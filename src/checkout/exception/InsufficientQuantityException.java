package checkout.exception;

public class InsufficientQuantityException extends RuntimeException {

    public InsufficientQuantityException() {
        super("Insufficient quantity in cart. Cannot remove more than the stored quantity");
    }

}
