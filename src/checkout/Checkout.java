package checkout;

import checkout.exception.InsufficientQuantityException;
import checkout.exception.ProductNotInCartException;
import checkout.exception.ZeroQuantityException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * The Checkout class extends the Cart class and provides additional
 * functionality to register consumers (listeners) for events related to product
 * manipulation in the cart. It supports listeners for adding, removing, and
 * clearing products from the cart.
 */
public class Checkout extends Cart {

    private final List<BiConsumer<Product, Integer>> addAction;
    private final List<BiConsumer<Product, Integer>> removeAction;
    private final List<Consumer<LinkedHashMap<Product, Integer>>> clearAction;
    private final List<Consumer<LinkedHashMap<Product, Integer>>> action;

    /**
     * Constructs a new Checkout object with empty lists to store the registered
     * consumers for add, remove, clear, and any action events.
     */
    public Checkout() {
        this.addAction = new ArrayList<>();
        this.removeAction = new ArrayList<>();
        this.clearAction = new ArrayList<>();
        this.action = new ArrayList<>();
    }

    /**
     * Add a consumer to be executed when a product is added to the cart.
     *
     * @param consumer The consumer to be added for the add event.
     */
    public void onAdd(BiConsumer<Product, Integer> consumer) {
        addAction.add(consumer);
    }

    /**
     * Add a consumer to be executed when a product is removed from the cart.
     *
     * @param consumer The consumer to be added for the remove event.
     */
    public void onRemove(BiConsumer<Product, Integer> consumer) {
        removeAction.add(consumer);
    }

    /**
     * Add a consumer to be executed when the cart is cleared.
     *
     * @param consumer The consumer to be added for the clear event.
     */
    public void onClear(Consumer<LinkedHashMap<Product, Integer>> consumer) {
        clearAction.add(consumer);
    }

    /**
     * Add a consumer to be executed on any action (add, remove, or clear).
     *
     * @param consumer The consumer to be added for any action event.
     */
    public void onAction(Consumer<LinkedHashMap<Product, Integer>> consumer) {
        action.add(consumer);
    }

    /**
     * Overrides the add method from the Cart class to include additional checks
     * and actions. It throws a ZeroQuantityException if the quantity is zero or
     * negative and fires the registered listeners for the add event.
     *
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product to be added.
     * @throws ZeroQuantityException If the quantity is zero or negative.
     */
    @Override
    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new ZeroQuantityException();
        }

        this.fireCheckListener(addAction, product, quantity);

        int newQuantity = quantity + getCount(product);
        this.putToCart(product, newQuantity);
    }

    /**
     * Overrides the remove method from the Cart class to include additional
     * checks and actions. It throws a ZeroQuantityException if the quantity is
     * zero or negative, a ProductNotInCartException if the product is not
     * present in the cart, and an InsufficientQuantityException if the
     * requested quantity to remove exceeds the stored quantity. It also fires
     * the registered listeners for the remove event.
     *
     * @param product The product to be removed from the cart.
     * @param quantity The quantity of the product to be removed.
     * @throws ZeroQuantityException If the quantity is zero or negative.
     * @throws ProductNotInCartException If the product is not present in the
     * cart.
     * @throws InsufficientQuantityException If the requested quantity to remove
     * exceeds the stored quantity.
     */
    @Override
    public void remove(Product product, int quantity) {
        if (quantity <= 0) {
            throw new ZeroQuantityException();
        }

        if (!this.inCart(product)) {
            throw new ProductNotInCartException();
        }

        if (this.getCount(product) < quantity) {
            throw new InsufficientQuantityException();
        }

        
        this.fireCheckListener(removeAction, product, quantity);

        int newQuantity = getCount(product) - quantity;
        this.putToCart(product, newQuantity);

    }

    /**
     * Overrides the putToCart method from the Cart class to fire the registered
     * listeners for any action event (add, remove, or clear) after putting the
     * product and quantity into the cart.
     *
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product to be added.
     */
    @Override
    public void putToCart(Product product, int quantity) {
        super.putToCart(product, quantity);
        
       //remove product whose quantity is zero
        if(this.getCount(product) <= 0){
            this.getCart().remove(product);
        }

        this.fireListener(this.action);
    }

    /**
     * Overrides the clear method from the Cart class to fire the registered
     * listeners for the clear event before clearing the cart (removing all
     * items).
     */
    @Override
    public void clear() {
        this.fireListener(this.clearAction);
        super.clear();
        this.fireListener(action);
    }

    /**
     * Utility method to execute all the registered listeners for a specific
     * event.
     *
     * @param listeners The list of listeners to be executed.
     * @param product The product related to the event.
     * @param quantity The quantity related to the event.
     */
    private void fireCheckListener(List<BiConsumer<Product, Integer>> listeners, Product product, int quantity) {
        for (var listener : listeners) {
            listener.accept(product, quantity);
        }
    }

    /**
     * Utility method to execute all the registered listeners for a specific
     * event.
     *
     * @param listeners The list of listeners to be executed.
     */
    private void fireListener(List<Consumer<LinkedHashMap<Product, Integer>>> listeners) {
        for (var listener : listeners) {
            listener.accept(this.getCart());
        }
    }
}
