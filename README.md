# Checkout System

The Checkout System is a Java library that allows you to manage a shopping cart and calculate the total cost of products in the cart. It uses `BigDecimal` to ensure accurate financial calculations with 2 decimal places, avoiding the precision issues of using `double`.

## Features

- Add products to the cart with quantities.
- Remove products from the cart with quantities.
- Clear the cart (remove all items).
- Calculate the total cost of products in the cart.
- Transaction

## Transaction Class

The `Transaction` class facilitates the logic for calculating the total, cash amount, and change. You can also add listener so you can hook up event for the view

### Constructor

```java
public Transaction(Consumer<BigDecimal> totalAction, Consumer<BigDecimal> cashAction, Consumer<BigDecimal> changeAction)
```

Creates a new `Transaction` object with the specified actions for updating the total, cash, and change in the checkout view.

- `totalAction`: The action to be called when the total needs to be updated in the view.
- `cashAction`: The action to be called when the cash amount needs to be updated in the view.
- `changeAction`: The action to be called when the change amount needs to be updated in the view.

### `run()`

```java
public void run()
```

This method should be called every time the cart changes or the user inputs the cash. Before calling this method, ensure that you have set the cash or total amount so that it remains synchronized with the cart value.

- The method updates the total in the view by calling the `totalAction`.
- If the cash amount is less than or equal to zero, the method returns without further calculations.
- If the cash amount is greater than zero and less than the total amount, the method does not throw an exception, but it might be an indication of insufficient cash for the transaction.
- The method calculates the change by subtracting the total from the cash and updates the cash and change in the view using the `cashAction` and `changeAction` respectively.

### `setTotal(double total)`

```java
public void setTotal(double total)
```

This method sets the total amount for the transaction.

- `total`: The total amount as a `double`.

### `setCash(double cash)`

```java
public void setCash(double cash)
```

This method sets the cash amount for the transaction.

- `cash`: The cash amount as a `double`.

Please ensure that you use these methods appropriately to update the cash and total amounts before calling the `run()` method to reflect the changes in the view.

Note: There is a commented section that indicates a possible exception (`InsufficientAmountException`) that can be thrown if the cash amount is less than the total amount. If this behavior is desired, you can uncomment the relevant lines and create the `InsufficientAmountException` class to handle such situations.

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
Checkout cart = new Checkout();
```

### 3. Add Products to the Cart

Add products to the cart using the `add` method:

```java
Product product1 = new MyProduct();
cart.add(product1, 2);
```

### 4. Remove Products from the Cart

Remove products from the cart using the `remove` method:

```java
cart.remove(product, 1);
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
Checkout cart = new Checkout();

// Register a listener for the add event
cart.onAdd((product, quantity) -> System.out.println(quantity + "x " + product.getName() + " added to cart."));

// Register a listener for the remove event
cart.onRemove((product, quantity) -> System.out.println(quantity + "x " + product.getName() + " removed from cart."));

// Register a listener for the clear event
//Note: products is a LinkedHashMap<Product,Integer>
cart.onClear(products -> System.out.println("Cart cleared."));

// Register a listener for any action (add, remove, or clear)
//Note: products is a LinkedHashMap<Product,Integer>
cart.onAction(proudcts -> System.out.println("Cart action occurred."));
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.