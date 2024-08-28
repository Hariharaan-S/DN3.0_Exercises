package com.bookstoreapi.bookstoreapi.controller;

import com.bookstoreapi.bookstoreapi.assemblers.BookDTOAssembler;
import com.bookstoreapi.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.bookstoreapi.entity.Book;
import com.bookstoreapi.bookstoreapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookDTOAssembler bookDTOAssembler;

    @PostMapping(value = "/add/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@Valid @RequestBody Book newBook) {
        return bookService.convertToBookDto(bookService.saveBook(newBook));
    }

    @GetMapping(value = "/get/books")
    public ResponseEntity<CollectionModel<BookDTO>> getAllBooksFromDatabase() {
        try {
            List<BookDTO> books = bookService.getAllBooks().stream()
                    .map(bookDTOAssembler::toModel)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(CollectionModel.of(books));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/get/book/{bookId}")
    public ResponseEntity<List<BookDTO>> getSingleBook(@PathVariable("bookId") Integer bookId) {
        try {

            return new ResponseEntity<>(bookService.findUsingBookID(bookId), HttpStatusCode.valueOf(200));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping(value = "/get/custom-books")
    public List<BookDTO> getBooksByQuery(@RequestParam(required = false) String title,
                                         @RequestParam(required = false) String author) {
        if (title != null && author != null) {
            return bookService.findUsingTitleAndAuthor(title, author);
        } else if (title != null) {
            return bookService.findUsingTitle(title);
        } else if (author != null) {
            return bookService.findUsingAuthor(author);
        }
        return List.of();
    }

    @PutMapping(value = "/update/book")
    public ResponseEntity<BookDTO> updateABook(@Valid @RequestBody Book updatedBook) {
        try {
            return new ResponseEntity<>(bookService.convertToBookDto(bookService.updateBook(updatedBook)), HttpStatusCode.valueOf(300));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @DeleteMapping(value = "/delete/book")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteABook(@RequestBody Integer bookID) {
        bookService.deleteBook(bookID);
    }
}
