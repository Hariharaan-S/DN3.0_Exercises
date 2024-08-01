/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;

import java.util.Scanner;

/**
 *
 * @author shari
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {
       System.out.println("================= EMPLOYEE MANAGEMENT SYSTEM ====================");
        BookController manageBook = new BookController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("================== MENU ===================");
            System.out.print("1. Add new book\n2. Search book (Binary Search)\n3. Search Book (Linear Search)\n4. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int bookID = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("Enter Book title: ");
                    String bookTitle = sc.nextLine();
                   
                    System.out.print("Enter Book Author: ");
                    String bookAuthor = sc.nextLine();
                    
                    manageBook.addBook(bookID, bookTitle, bookAuthor);
                    System.out.println("Book added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter Book ID: ");
                    int bookID = sc.nextInt();
                    sc.nextLine();
                    manageBook.searchBookBinarySearch(bookID);
                }

                case 3 -> {
                    System.out.print("Enter Book ID: ");
                    int bookID = sc.nextInt();
                    sc.nextLine();
                    manageBook.searchBookLinearSearch(bookID);
                }

                default -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
            }
        }
    }
}
