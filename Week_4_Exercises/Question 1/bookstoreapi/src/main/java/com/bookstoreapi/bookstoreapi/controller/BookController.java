package com.bookstoreapi.bookstore.controller;

import com.bookstoreapi.bookstore.entity.Book;
import com.bookstoreapi.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/add/book")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook){
        try{
            return new ResponseEntity<>(bookService.saveBook(newBook),HttpStatusCode.valueOf(200));
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping(value = "/get/books")
    public ResponseEntity<List<Book>> getAllBooksFromDatabase(){
        try{
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatusCode.valueOf(200));
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PutMapping(value = "/update/book")
    public ResponseEntity<Book> updateABook(@RequestBody Book updatedBook){
        try{
            return new ResponseEntity<>(bookService.updateBook(updatedBook), HttpStatusCode.valueOf(300));
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @DeleteMapping(value = "/delete/book")
    public ResponseEntity<HttpStatusCode> deleteABook(@RequestBody Integer bookID){
        try{
            bookService.deleteBook(bookID);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

}
