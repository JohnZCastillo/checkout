package checkout.exception;

public class ZeroQuantityException extends RuntimeException {

    public ZeroQuantityException() {
        super("Product quantity in cart cannot be zero or negative");
    }

}