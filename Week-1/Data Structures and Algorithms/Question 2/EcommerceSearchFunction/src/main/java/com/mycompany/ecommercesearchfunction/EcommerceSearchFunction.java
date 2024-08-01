/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ecommercesearchfunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author shari
 */
public class EcommerceSearchFunction {

    public static void main(String[] args) {

        Product[] productList = new Product[5];
        int[] productID = {1344, 5432, 2334, 8909, 8765};
        String[] productName = {"Men's shirt", "Women's chudi", "Miniature Airplane", "Airpods 16", "Samsung Galaxy S22"};
        String[] category = {"Men clothing", "Women clothing", "Toys", "Mobile Accessories", "Smartphone"};
        for (int index = 0; index < 5; index++) {
            Product product = new Product();
            product.setProductID(productID[index]);
            product.setProductName(productName[index]);
            product.setCategory(category[index]);
            productList[index] = product;
        }
        System.out.println("Search using Linear Search:");

        int searchProductID = 1344;
        boolean isFound = false;
        for (Product product : productList) {
            if (product.getProductID() == searchProductID) {
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Product category: " + product.getCategory());
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("No product found.");
        }
        System.out.println();
        System.out.println("Search using Binary Search:");
        Arrays.sort(productList);
        int left = 0;
        int right = productList.length - 1;
        while (left <= right) {
            int mid = (left + (right - left)) / 2;
            if (productList[mid].getProductID() == searchProductID) {
                Product foundProduct = productList[mid];
                System.out.println("Product ID: " + foundProduct.getProductID());
                System.out.println("Product Name: " + foundProduct.getProductName());
                System.out.println("Product category: " + foundProduct.getCategory());
                break;
            } else if (productList[mid].getProductID() < searchProductID) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

    }
}
