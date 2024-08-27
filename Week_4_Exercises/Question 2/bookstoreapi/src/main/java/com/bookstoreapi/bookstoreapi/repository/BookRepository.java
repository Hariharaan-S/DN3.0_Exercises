package com.bookstoreapi.bookstore.repository;

import com.bookstoreapi.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
