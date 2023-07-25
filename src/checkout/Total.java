package checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

/**
 * The Total class computes the total cost of the products in the cart using
 * BigDecimal with 2 decimal places. This ensures accurate and precise
 * calculations for financial purposes.
 */
public class Total {

    /**
     * Computes the total cost of the products in the cart using BigDecimal with
     * 2 decimal places.
     *
     * @param products The LinkedHashMap containing products as keys and their
     * quantities as values.
     * @return The total cost of the products in the cart with 2 decimal places
     * as a BigDecimal.
     */
    public BigDecimal compute(LinkedHashMap<Product, Integer> products) {
        BigDecimal total = BigDecimal.ZERO;

        for (var product : products.keySet()) {
            int quantity = products.get(product);
            BigDecimal price = BigDecimal.valueOf(product.getPrice());
            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity));
            total = total.add(itemTotal);
        }

        // Round the total to 2 decimal places using RoundingMode.HALF_UP
        total = total.setScale(2, RoundingMode.HALF_UP);

        return total;
    }
}
