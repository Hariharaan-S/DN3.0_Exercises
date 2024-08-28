package com.bookstoreapi.bookstoreapi.repository;

import com.bookstoreapi.bookstoreapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByBookName(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByBookNameAndAuthor(String title, String author);
}
