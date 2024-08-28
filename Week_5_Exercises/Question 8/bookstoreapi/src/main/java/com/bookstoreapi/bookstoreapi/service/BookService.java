package com.bookstoreapi.bookstoreapi.service;

import com.bookstoreapi.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.bookstoreapi.entity.Book;
import com.bookstoreapi.bookstoreapi.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Book saveBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    public Optional<Book> findBook(int bookID) {
        return bookRepository.findById(bookID);
    }

    public Book updateBook(Book updateBook) {
        return bookRepository.save(updateBook);
    }

    public void deleteBook(int bookID) {
        bookRepository.deleteById(bookID);
    }

    public List<BookDTO> findUsingBookID(int bookId) {
        return bookRepository.findById(bookId).stream().map(this::convertToBookDto).collect(Collectors.toList());
    }

    public List<BookDTO> findUsingTitle(String title) {
        return bookRepository.findByBookName(title).stream().map(this::convertToBookDto).collect(Collectors.toList());
    }

    public List<BookDTO> findUsingAuthor(String title) {
        return bookRepository.findByAuthor(title).stream().map(this::convertToBookDto).collect(Collectors.toList());
    }

    public List<BookDTO> findUsingTitleAndAuthor(String title, String author) {
        return bookRepository.findByBookNameAndAuthor(title, author).stream().map(this::convertToBookDto).collect(Collectors.toList());
    }

    public BookDTO convertToBookDto(Book book) {

        return modelMapper.map(book, BookDTO.class);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::convertToBookDto).collect(Collectors.toList());
    }
}
