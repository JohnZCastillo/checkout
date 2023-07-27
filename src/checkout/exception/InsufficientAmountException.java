
package checkout.exception;


public class InsufficientAmountException extends RuntimeException{
    
    public InsufficientAmountException(){
    super("Insufficient Amount");
    }
}
