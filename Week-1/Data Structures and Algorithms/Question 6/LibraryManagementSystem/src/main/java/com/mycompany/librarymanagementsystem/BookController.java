/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

/**
 *
 * @author shari
 */
public class BookController {

    private static final Book[] bookList = new Book[5];
    private static int numberOfBooks = 0;
    
    public void addBook(int bookID, String title, String author) {
        if (numberOfBooks == 5) {
            System.out.println("Cannot add new Books...");
            return;
        }
        Book newBook = new Book();
        newBook.setAuthor(author);
        newBook.setBookID(bookID);
        newBook.setTitle(title);

        for (int i = 0; i < bookList.length; i++) {
            if (bookList[i] == null) {
                bookList[i] = newBook;
                break;
            }
        }
        numberOfBooks++;
    }

    public void searchBookBinarySearch(int bookID) {
        boolean found = false;
        int left = 0;
        int right = bookList.length - 1;
        while (left <= right) {
            int mid = (left + (right - left)) / 2;
            if (bookList[mid].getBookID() == bookID) {
                Book foundBook = bookList[mid];
                System.out.println("Book found!");
                found = true;
                System.out.println("Book ID: " + foundBook.getBookID());
                System.out.println("Book Title: " + foundBook.getTitle());
                System.out.println("Book Author: " + foundBook.getAuthor());
                break;
                
            } else if (bookList[mid].getBookID() < bookID) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if(left == right || !found){
            System.out.println("Book Not Found!");
        }
    }
    
    public void searchBookLinearSearch(int bookID){
        boolean found = false;
        for(Book book: bookList){
            if(book.getBookID() == bookID){
                System.out.println("Book found!");
                found = true;
                System.out.println("Book ID: " + book.getBookID());
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Book Author: " + book.getAuthor());
                break;
            }
        }
        if(!found){
            System.out.println("Book Not Found!");
        }
    }
}
