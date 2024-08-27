package com.bookstoreapi.bookstore.service;

import com.bookstoreapi.bookstore.entity.Book;
import com.bookstoreapi.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book newBook){
        return bookRepository.save(newBook);
    }

    public Optional<Book> findBook(int bookID){
        return bookRepository.findById(bookID);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book updateBook(Book updateBook){
        return bookRepository.save(updateBook);
    }

    public void deleteBook(int bookID){
        bookRepository.deleteById(bookID);
    }
}
