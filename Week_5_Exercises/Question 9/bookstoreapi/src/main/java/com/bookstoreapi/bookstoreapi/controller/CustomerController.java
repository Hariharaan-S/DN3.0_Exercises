package com.bookstoreapi.bookstoreapi.controller;

import com.bookstoreapi.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entity.Customer;
import com.bookstoreapi.bookstoreapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/create/customer")
    public ResponseEntity<CustomerDTO> createANewCustomer(@Valid @RequestBody Customer newCustomer) {
        try {
            return new ResponseEntity<>(customerService.convertToCustomerDto(customerService.createNewCustomer(newCustomer)), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
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
            Customer customer = new Customer();
            customer.setCustomerName(name);
            customer.setCustomerID(id);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
            customer.setPassword(password);
            Customer savedCustomer = customerService.createNewCustomer(customer);
            return new ResponseEntity<>(customerService.convertToCustomerDto(savedCustomer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatusCode.valueOf(200));
    }
    @GetMapping(value = "/get/customer/{id}")
    public ResponseEntity<List<CustomerDTO>> getCustomerByID(@PathVariable Integer id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.FOUND);
    }

    @PutMapping(value = "/update/customer")
    public ResponseEntity<CustomerDTO>updateCustomerDetails(@Valid @RequestBody Customer updatedCustomer){
        return new ResponseEntity<>(customerService.convertToCustomerDto(customerService.updateCustomer(updatedCustomer.getCustomerID(), updatedCustomer.getCustomerName(), updatedCustomer.getEmail(), updatedCustomer.getPassword(), updatedCustomer.getPhoneNumber())),HttpStatusCode.valueOf(200));
    }

    @DeleteMapping(value = "/delete/customer")
    public void deleteCustomer(@RequestBody Integer id){
        customerService.deleteCustomer(id);
    }
}
