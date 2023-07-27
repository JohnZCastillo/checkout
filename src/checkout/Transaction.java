package checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Consumer;

/**
 * Bootstrap the logic for displaying total, cash, and change in checkout view.
 */
public class Transaction {

    private BigDecimal total;
    private BigDecimal cash;
    private BigDecimal change;

    // Actions to be called upon update.
    private final Consumer<BigDecimal> totalAction;
    private final Consumer<BigDecimal> cashAction;
    private final Consumer<BigDecimal> changeAction;

    /**
     * Creates a new Transaction with the specified actions for updating the
     * total, cash, and change in the checkout view.
     *
     * @param totalAction The action to be called when the total needs to be
     * updated in the view.
     * @param cashAction The action to be called when the cash amount needs to
     * be updated in the view.
     * @param changeAction The action to be called when the change amount needs
     * to be updated in the view.
     */
    public Transaction(Consumer<BigDecimal> totalAction, Consumer<BigDecimal> cashAction, Consumer<BigDecimal> changeAction) {
        this.totalAction = totalAction;
        this.cashAction = cashAction;
        this.changeAction = changeAction;
        total = BigDecimal.ZERO;
        cash = BigDecimal.ZERO;
        change = BigDecimal.ZERO;
    }

    /**
     * Run this every time the cart changes or the user inputs the cash.
     *
     * Note: Before calling this method, you must first set the cash or total,
     * so that it's in sync with the value of the cart.
     */
    public void run() {

        // Update the total in the view.
        totalAction.accept(total);

        if (cash.doubleValue() <= 0) {
            return;
        }

        if (cash.doubleValue() != 0 && total.doubleValue() > cash.doubleValue()) {
//            throw new InsufficientAmountException();
        }

        // Calculate the change and update the cash and change in the view.
        change = cash.subtract(total);
        change.setScale(2, RoundingMode.CEILING);

        cashAction.accept(cash);
        changeAction.accept(change);
    }

    /**
     * Sets the total amount for the transaction.
     *
     * @param total The total amount.
     */
    public void setTotal(double total) {
        this.total = BigDecimal.valueOf(total);
    }

    /**
     * Sets the cash amount for the transaction.
     *
     * @param cash The cash amount.
     */
    public void setCash(double cash) {
        this.cash = BigDecimal.valueOf(cash);
    }

}
