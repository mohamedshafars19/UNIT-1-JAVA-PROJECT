
import java.util.*;
import java.util.Scanner;

class Product{
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

}

class CartItem {
    private Product product;
    private int quantity;
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void increaseQuantity(int qyt){
        quantity += qyt;
    }

    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}

class ShoppingCart {
    private Map<Integer, CartItem> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        if (items.containsKey(product.getId())) {
            items.get(product.getId()).increaseQuantity(quantity);
        } else {
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n--- Shopping Cart ---");
        for (CartItem item : items.values()) {
            System.out.println(
                item.getProduct().getName() +
                " | Qty: " + item.getQuantity() +
                " | Total: ₹" + item.getTotalPrice()
            );
        }
        System.out.println("Grand Total: ₹" + getGrandTotal());
    }

    private double getGrandTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getTotalPrice();
        }
        return total;
    }

}

class Shop {
    private Scanner scanner = new Scanner(System.in);
    private ShoppingCart cart = new ShoppingCart();
    private Product[] products;

    public Shop() {
        products = new Product[] {
            new Product(1, "Laptop", 55000),
            new Product(2, "Headphones", 2500),
            new Product(3, "Mouse", 800),
            new Product(4, "Keyboard", 1500)
        };
    }

    public void start() {
        int choice = 0;

        while (choice != 4) {
            showMenu();
            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            if (choice == 1) {
                showProducts();
            } else if (choice == 2) {
                addToCart();
            } else if (choice == 3) {
                cart.showCart();
            } else if (choice == 4) {
                System.out.println("Checkout completed.");
                cart.showCart();
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Online Shopping Cart ===");
        System.out.println("1. View Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    private void showProducts() {
        for (Product p : products) {
            System.out.println(
                p.getId() + ". " + p.getName() + " - ₹" + p.getPrice()
            );
        }
    }

    private void addToCart() {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter Quantity: ");
        int qty = scanner.nextInt();

        for (Product p : products) {
            if (p.getId() == id) {
                cart.addProduct(p, qty);
                System.out.println("Product added.");
                return;
            }
        }
        System.out.println("Product not found.");
    }
}



public class OnlineShopping {
    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.start();
    }
}