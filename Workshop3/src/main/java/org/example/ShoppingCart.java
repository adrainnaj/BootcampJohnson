package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart(){
        this.products = new ArrayList<>();
    }

    //TODO add product to cart method
    public void addProductToCart(Product product){
        products.add(product);
    }

    //TODO remove product from cart method
    //You will need the sku of the product you want to remove
    //Loop through the list of products
    //Check to see if the sku matches
    //Get that product, then use the remove method after the loop
    public void removeProduct(String sku){
        Product toRemove = null;
        for (Product product : products){
            if (product.getSku().equalsIgnoreCase(sku)){
                toRemove = product;
                break;
            }
        }
        if (toRemove != null){
            products.remove(toRemove);
            System.out.println("Remove from cart:" + toRemove.getProductName()) ;
        }
    }
    //TODO get cart total method
    public double getCartTotal(){
        throw new UnsupportedOperationException();
    }
}
