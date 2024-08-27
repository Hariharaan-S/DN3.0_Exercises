package com.bookstoreapi.bookstoreapi.service;

import com.bookstoreapi.bookstoreapi.entity.Customer;
import com.bookstoreapi.bookstoreapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createNewCustomer(Customer newCustomer){
        return customerRepository.save(newCustomer);
    }
}
