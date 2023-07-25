# Checkout System

The Checkout System is a Java library that allows you to manage a shopping cart and calculate the total cost of products in the cart. It uses `BigDecimal` to ensure accurate financial calculations with 2 decimal places, avoiding the precision issues of using `double`.

## Features

- Add products to the cart with quantities.
- Remove products from the cart with quantities.
- Clear the cart (remove all items).
- Calculate the total cost of products in the cart.

## Usage

### 1. Create Products

Implement the `Product` interface to create your custom product class. The interface provides methods to retrieve product details such as name, description, barcode, and price.

Example:

```java
public class MyProduct implements Product {
    private String name;
    private String description;
    private String barcode;
    private double price;

    // Implement the methods from the Product interface
}
```

### 2. Create a Cart

To manage the shopping cart, create an instance of the `Cart` class:

```java
Cart cart = new Cart();
```

### 3. Add Products to the Cart

Add products to the cart using the `add` method:

```java
Product product1 = new MyProduct("Product A", "Description of Product A", "1234567890", 10.99);
cart.add(product1, 2);

Product product2 = new MyProduct("Product B", "Description of Product B", "0987654321", 5.49);
cart.add(product2, 3);
```

### 4. Remove Products from the Cart

Remove products from the cart using the `remove` method:

```java
cart.remove(product1, 1);
```

### 5. Calculate the Total Cost

Calculate the total cost of products in the cart using the `Total` class:

```java
Total totalCalculator = new Total();
BigDecimal totalCost = totalCalculator.compute(cart.getCart());
System.out.println("Total cost: $" + totalCost);
```

### 6. Register Listeners for Cart Events

You can register listeners (consumers) to be executed when specific events occur in the cart, such as adding, removing, or clearing products.

Example:

```java
Checkout checkout = new Checkout();

// Register a listener for the add event
checkout.onAdd((product, quantity) -> System.out.println(quantity + "x " + product.getName() + " added to cart."));

// Register a listener for the remove event
checkout.onRemove((product, quantity) -> System.out.println(quantity + "x " + product.getName() + " removed from cart."));

// Register a listener for the clear event
checkout.onClear(cart -> System.out.println("Cart cleared."));

// Register a listener for any action (add, remove, or clear)
checkout.onAction(cart -> System.out.println("Cart action occurred."));
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.