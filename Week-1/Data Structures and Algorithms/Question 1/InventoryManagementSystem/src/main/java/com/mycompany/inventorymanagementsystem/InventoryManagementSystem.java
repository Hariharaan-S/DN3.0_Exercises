/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inventorymanagementsystem;

import java.util.Scanner;

/**
 *
 * @author shari
 */
public class InventoryManagementSystem {

    public static void main(String[] args) {
        System.out.println("================= INVENTORY MANAGEMENT ====================");
        ProductController manageProduct = new ProductController();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("================== MENU ===================");
            System.out.print("1. Add new product\n2. Update product\n3. Delete product\n4. Display products\n5. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();  
            sc.nextLine();
            switch(choice){
                case 1 -> {
                    System.out.println("Enter product category: ");
                    String category = sc.nextLine();
                    System.out.println("Enter productID: ");
                    int productID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter productName: ");
                    String productName = sc.nextLine();
                    System.out.println("Enter quantity: ");
                    int quantity = sc.nextInt();
                    System.out.println("Enter unit price: ");
                    int price = sc.nextInt();
                    Product newProduct = manageProduct.createProduct(productID, productName, quantity, price);
                    manageProduct.addProduct(newProduct,category);
                    break;
                }
                case 2 -> {
                    System.out.println("Enter product category: ");
                    String category = sc.nextLine();
                    System.out.println("Enter the product ID to be updated");
                    int productID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter what you want to change:");
                    System.out.println("1. Unit price\n2. Quantity");
                    System.out.println("Enter Choice:");
                    int type = sc.nextInt();
                    sc.nextLine();
                    manageProduct.updateProduct(type, category, productID);
                    break;
                }
                
                case 3 -> {
                    System.out.println("Enter product category: ");
                    String category = sc.nextLine();
                    System.out.println("Enter the product ID to be deleted:");
                    int productID = sc.nextInt();
                    sc.nextLine();
                    manageProduct.deleteProduct(category, productID);
                    break;
                }
                
                case 4 -> {
                    System.out.println("Enter product category: ");
                    String category = sc.nextLine();
                    manageProduct.displayProducts(category);
                    break;
                }
                default -> {
                    break;
                }
            }
            if(choice == 5){
                break;
            }
        }

    }
}
