package com.bookstoreapi.bookstoreapi.service;

import com.bookstoreapi.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entity.Customer;
import com.bookstoreapi.bookstoreapi.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Customer createNewCustomer(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    public List<CustomerDTO> getCustomerById(Integer id){
        return customerRepository.findById(id).stream().map(this::convertToCustomerDto).collect(Collectors.toList());
    }

    @Transactional
    public Customer updateCustomer(Integer id, String newName, String newEmail, String newPassword, String newPhoneNumber) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));


        existingCustomer.setCustomerName(newName);
        existingCustomer.setPassword(newPassword);
        existingCustomer.setPhoneNumber(newPhoneNumber);
        existingCustomer.setEmail(newEmail);

        return customerRepository.save(existingCustomer);
    }


    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }

    public CustomerDTO convertToCustomerDto(Customer customer) {

        return modelMapper.map(customer, CustomerDTO.class);
    }


    public List<CustomerDTO> getAllCustomers(){
        return customerRepository.findAll().stream().map(this::convertToCustomerDto).collect(Collectors.toList());
    }
}
