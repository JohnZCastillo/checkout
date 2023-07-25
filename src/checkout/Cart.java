package checkout;

import java.util.LinkedHashMap;

/**
 * The Cart class represents a shopping cart that holds products and their respective quantities.
 * It uses a LinkedHashMap to store the products as keys and their corresponding quantities as values.
 */
public class Cart {

    // LinkedHashMap to store products and their quantities in the cart
    private LinkedHashMap<Product, Integer> cart;

    /**
     * Constructs an empty Cart object with an empty LinkedHashMap.
     */
    public Cart() {
        this.cart = new LinkedHashMap<>();
    }

    /**
     * Adds a given quantity of a product to the cart. If the product already exists in the cart,
     * the quantity will be updated by adding the given quantity to the existing quantity.
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product to be added.
     */
    public void add(Product product, int quantity){
        int newQuantity = quantity + getCount(product);
        this.putToCart(product, newQuantity);
    }

    /**
     * Removes a given quantity of a product from the cart. If the product already exists in the cart,
     * the quantity will be updated by subtracting the given quantity from the existing quantity.
     * @param product The product to be removed from the cart.
     * @param quantity The quantity of the product to be removed.
     */
    public void remove(Product product, int quantity){
        int newQuantity = getCount(product) - quantity;
        this.putToCart(product, newQuantity);
    }

    /**
     * Checks if a specific product is present in the cart.
     * @param product The product to be checked.
     * @return true if the product is present in the cart, false otherwise.
     */
    public boolean inCart(Product product) {
        return cart.containsKey(product);
    }

    /**
     * Gets the quantity of a specific product in the cart.
     * @param product The product whose quantity is to be retrieved.
     * @return The quantity of the specified product in the cart. If the product is not found, returns 0.
     */
    public int getCount(Product product) {
        return cart.containsKey(product) ? cart.get(product) : 0;
    }

    /**
     * Clears all the items from the cart, making it empty.
     */
    public void clear() {
        this.cart.clear();
    }

    /**
     * Puts a given product with its quantity into the cart.
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product to be added.
     */
    public void putToCart(Product product, int quantity) {
        cart.put(product, quantity);
    }

    /**
     * Retrieves the LinkedHashMap representing the cart items.
     * @return The LinkedHashMap containing products as keys and their quantities as values.
     */
    public LinkedHashMap<Product, Integer> getCart() {
        return cart;
    }
}
