package com.bookstoreapi.bookstoreapi.repository;

import com.bookstoreapi.bookstoreapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
