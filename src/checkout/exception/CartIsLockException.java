package checkout.exception;

public class CartIsLockException extends RuntimeException {

    public CartIsLockException() {
        super("Cannot Modify Cart Content because it is locked!");
    }

}
