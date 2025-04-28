package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = FileLoader.readFile();
        ShoppingCart cart = new ShoppingCart();


        while (true) {
            System.out.println("Welcome to the store! Choose an option:");
            System.out.println("1. View all products");
            System.out.println("2. Search by SKU");
            System.out.println("3. Search by price range");
            System.out.println("4. Search by name");
            System.out.println("5. Add to cart");
            System.out.println("6. Remove from cart");
            System.out.println("7. View cart");
            System.out.println("8. Checkout");
            System.out.println("9. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayProducts(products);
                    return;
                case 2:
                    System.out.println("Enter the SKU of the product your looking for: ");
                    String sku = scanner.nextLine();
                    Product foundProduct = searchProductSku(products, sku);

                    if (foundProduct != null) {
                        System.out.println("Found: " + foundProduct.getProductName());
                    } else {
                        System.out.println("Product not found");
                    }
                    return;
                case 3:
                    System.out.println("Enter minimum price: ");
                    double min = scanner.nextDouble();
                    System.out.println("Enter maximum price");
                    double max = scanner.nextDouble();
                    List<Product> filtered = filterByPriceRange(products, min, max);
                    if (filtered.isEmpty()) {
                        System.out.println("No products in that price range.");
                    }
                    else {
                        System.out.println("Filtered Products: ");
                        for (Product product : filtered){
                            System.out.println(product);
                        }
                    }
                    return;
                case 4:
                    System.out.println("Enter Product Name");
                    String productName = scanner.nextLine();
                    List<Product> matchingProducts = searchByName(products, productName);

                    if (matchingProducts.isEmpty()) {
                        System.out.println("No products found");
                    }
                        for(Product product : matchingProducts){
                            System.out.println(product.getProductName() + " - $" + product.getPrice());
                        }
                    return;
                case 5:
                    System.out.println("Enter SKU to add to cart: ");
                    String skuToAdd = scanner.nextLine();
                    Product productToAdd = searchProductSku(products, skuToAdd);

                    if ( productToAdd != null){
                        cart.addProductToCart(productToAdd);
                        System.out.println("Added to cart: " + productToAdd.getProductName());
                    }
                    else{
                        System.out.println("Product not found.");
                    }
                    return;
                case 6:
                    System.out.println("Enter SKU to remove from cart: ");
                    String skuToRemove = scanner.nextLine();
                    Product productToRemove = searchProductSku(products, skuToRemove);

                    if(productToRemove != null){
                        cart.removeProduct(skuToRemove);
                        System.out.println("Remove from cart: " + productToRemove.getProductName());
                    }
                    else {
                        System.out.println("Product not found.");
                    }
                    return;

                case 7:

                    break;
                case 8:
                    // Display total and thank the user
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println("SKU: " + product.getSku() + " | " + product.getProductName() + "  | " +
                    "$" + product.getPrice() + " | " + product.getDepartment());
        }
    }

    public static Product searchProductSku(List<Product> products, String sku) {
        for (Product product : products) {
            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }
        return null;
    }

    public static List<Product> searchByName(List<Product> products, String productName) {
        List<Product> matches = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                matches.add(product);
            }
        }
        return matches;
    }

    public static List<Product> filterByPriceRange(List<Product> products, double min, double max) {
        List<Product> matches = new ArrayList<>();
        for(Product product : products){
            if (product.getPrice() >= min && product.getPrice() <= max){
                matches.add(product);
            }
        }
        return matches;
    }
    public static void viewCart(){
    }
}