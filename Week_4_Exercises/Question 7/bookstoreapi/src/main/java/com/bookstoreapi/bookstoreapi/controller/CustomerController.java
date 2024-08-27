package com.bookstoreapi.bookstoreapi.controller;

import com.bookstoreapi.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entity.Customer;
import com.bookstoreapi.bookstoreapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/create/customer")
    public ResponseEntity<CustomerDTO> createANewCustomer(@RequestBody Customer newCustomer){
        try{
            return new ResponseEntity<>(customerService.convertToCustomerDto(customerService.createNewCustomer(newCustomer)), HttpStatusCode.valueOf(200));
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> registerCustomer(
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String password) {
        try {
            Customer customer = new Customer(id, name, email, phoneNumber, password);
            Customer savedCustomer = customerService.createNewCustomer(customer);
            return new ResponseEntity<>(customerService.convertToCustomerDto(savedCustomer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
