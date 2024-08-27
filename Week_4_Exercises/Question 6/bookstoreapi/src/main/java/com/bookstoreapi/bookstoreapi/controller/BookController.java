package com.bookstoreapi.bookstoreapi.controller;

import com.bookstoreapi.bookstoreapi.entity.Book;
import com.bookstoreapi.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/add/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book newBook){
        return bookService.saveBook(newBook);
    }

    @GetMapping(value = "/get/books")
    public ResponseEntity<List<Book>> getAllBooksFromDatabase(){
        try{
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatusCode.valueOf(200));
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping(value = "/get/book/{bookId}")
    public ResponseEntity<Optional<Book>> getSingleBook(@PathVariable("bookId") Integer bookId){
        try{
            return new ResponseEntity<>(bookService.findUsingBookID(bookId),HttpStatusCode.valueOf(200));
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping(value = "/get/custom-books")
    public List<Book> getBooksByQuery(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) String author) {
        if (title != null && author != null) {
            return bookService.findUsingTitleAndAuthor(title, author);
        } else if (title != null) {
            return bookService.findUsingTitle(title);
        } else if (author != null) {
            return bookService.findUsingAuthor(author);
        } else {
            return bookService.getAllBooks();
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteABook(@RequestBody Integer bookID){
        bookService.deleteBook(bookID);
    }

}
