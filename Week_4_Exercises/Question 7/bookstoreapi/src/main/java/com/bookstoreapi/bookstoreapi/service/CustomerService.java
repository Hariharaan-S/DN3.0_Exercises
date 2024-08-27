package com.bookstoreapi.bookstoreapi.service;

import com.bookstoreapi.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entity.Customer;
import com.bookstoreapi.bookstoreapi.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Customer createNewCustomer(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    public CustomerDTO convertToCustomerDto(Customer customer) {

        return modelMapper.map(customer, CustomerDTO.class);
    }
}
