package com.librarymanagement.LibraryManagement;

import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        BookRepository bookRepo = new BookRepository();
        BookService bookService = new BookService();
        bookService.setBookRepository(bookRepo);
        bookService.addBookToRepository("Harry Potter");
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

}
