package checkout;

/**
 * The Product interface represents a product that can be added to the checkout
 * system. Any class implementing this interface must provide concrete
 * implementations for all the methods.
 */
public interface Product {

    /**
     * Gets the name of the product.
     *
     * @return The name of the product as a String.
     */
    String getName();

    /**
     * Gets the description of the product.
     *
     * @return The description of the product as a String.
     */
    String getDescription();

    /**
     * Gets the barcode of the product.
     *
     * @return The barcode of the product as a String.
     */
    String getBarcode();

    /**
     * Gets the price of the product.
     *
     * @return The price of the product as a double value.
     */
    double getPrice();
}
