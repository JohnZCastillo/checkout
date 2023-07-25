package checkout.exception;

public class ProductNotInCartException extends RuntimeException {

    public ProductNotInCartException() {
        super("Product not found in cart");
    }
}
