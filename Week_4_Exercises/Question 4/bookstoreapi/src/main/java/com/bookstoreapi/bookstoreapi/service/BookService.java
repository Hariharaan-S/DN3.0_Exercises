package com.bookstoreapi.bookstoreapi.service;

import com.bookstoreapi.bookstoreapi.entity.Book;
import com.bookstoreapi.bookstoreapi.repository.BookRepository;
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

    public Optional<Book> findUsingBookID(int bookId){
        return bookRepository.findById(bookId);
    }

    public List<Book> findUsingTitle(String title){
        return bookRepository.findByBookName(title);
    }

    public List<Book> findUsingAuthor(String title){
        return bookRepository.findByAuthor(title);
    }

    public List<Book> findUsingTitleAndAuthor(String title, String author){ return bookRepository.findByBookNameAndAuthor(title, author); }
}
