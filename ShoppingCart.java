import java.util.HashMap;
import java.util.Map;

class ShopCart {
    private Map<String, Integer> cart;
    private Map<String, Double> priceList; // Store prices

    // Constructor
    public ShopCart() {
        cart = new HashMap<>();
        priceList = new HashMap<>();

        // Predefined product prices
        priceList.put("Apple", 50.0);
        priceList.put("Milk", 30.0);
        priceList.put("Bread", 40.0);
    }

    // Add product
    public void addProduct(String product, int quantity) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
        System.out.println(product + " added to cart.");
    }

    // Remove product
    public void removeProduct(String product) {
        if (cart.containsKey(product)) {
            cart.remove(product);
            System.out.println(product + " removed from cart.");
        } else {
            System.out.println(product + " not found in cart.");
        }
    }

    // Update quantity
    public void updateProduct(String product, int quantity) {
        if (cart.containsKey(product)) {
            cart.put(product, quantity);
            System.out.println(product + " quantity updated.");
        } else {
            System.out.println(product + " not found in cart.");
        }
    }

    // Display cart
    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    // NEW METHOD: View Total Cost
    public void viewTotalCost() {
        double total = 0;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();

            double price = priceList.getOrDefault(product, 0.0);
            total += price * quantity;
        }

        System.out.println("Total Cost: ₹" + total);
    }
}

// Main class
public class ShoppingCart {
    public static void main(String[] args) {
        ShopCart cart = new ShopCart();

        cart.addProduct("Apple", 2);
        cart.addProduct("Milk", 1);
        cart.addProduct("Apple", 3);

        cart.displayCart();

        cart.updateProduct("Milk", 5);
        cart.removeProduct("Apple");

        cart.displayCart();

        // NEW FEATURE CALL
        cart.viewTotalCost();
    }
}