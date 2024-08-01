/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorymanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

/**
 *
 * @author shari
 */
public class ProductController {

    private static final HashMap<String, List<Product>> productHashMap = new HashMap<>();

    private static boolean isGreater(Product a, Product b) {
        return a.getProductID() >= b.getProductID();
    }

    private static List<Product> sortListAscending(List<Product> productList) {
        Collections.sort(productList);
        return productList;
    }

    public Product createProduct(int productID, String productName, int quantity, int price) {
        Product product = new Product();
        product.setProductID(productID);
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setUnitPrice(price);

        return product;
    }

    public void addProduct(Product product, String category) {
        List<Product> products = null;
        if (productHashMap.containsKey(category)) {
            products = productHashMap.get(category);
            products.add(product);
        } else {
            products = new ArrayList<>();
            products.add(product);
        }
        productHashMap.put(category, products);
        System.out.println("Product Added Successfully");
    }

    public void updateProduct(int type, String category, int productID) {
        List<Product> productList = productHashMap.get(category);
        Product tobeUpdatedProduct = null;
        for (Product p : productList) {
            if (p.getProductID() == productID) {
                tobeUpdatedProduct = p;
                break;
            }
        }

        if (tobeUpdatedProduct == null) {
            System.out.println("No product there..");
            return;
        }

        Scanner sc = new Scanner(System.in);
        switch (type) {
            case 1 -> {
                System.out.print("Enter the updated unit price: ");
                int updatedPrice = sc.nextInt();
                tobeUpdatedProduct.setUnitPrice(updatedPrice);
                System.out.println("Product " + tobeUpdatedProduct.getProductID() + " is updated successfully!");
                break;
            }
            case 2 -> {
                System.out.print("Enter updated quantity: ");
                int updatedQuantity = sc.nextInt();
                tobeUpdatedProduct.setQuantity(updatedQuantity);
                System.out.println("Product updated successfully!");
                break;
            }
        }
    }

    public void deleteProduct(String category, int productID) {
        List<Product> productList = productHashMap.get(category);
        Product tobeDeletedProduct = null;
        int position = 0;
        for (Product p : productList) {
            position++;
            if (p.getProductID() == productID) {
                tobeDeletedProduct = p;
                break;
            }
        }
        productList.remove(position);
        productHashMap.put(category, productList);
        System.out.println("Product deleted successfully!");
    }

    public void displayProducts(String category) {
        if (!productHashMap.containsKey(category)) {
            System.out.println("No products there...");
        } else {
            List<Product> products = productHashMap.get(category);
            for (Product product : products) {
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Product quantity: " + product.getQuantity());
                System.out.println("Product unit price: " + product.getUnitPrice());
                System.out.println("Product total price: " + product.getUnitPrice() * product.getQuantity());
                System.out.println();

            }
        }

    }
}
