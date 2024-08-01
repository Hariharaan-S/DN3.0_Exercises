/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dependencyinjectionexample;

/**
 *
 * @author shari
 */
public class CustomerService {
    private CustomerRepository customerRepo;
    
    public CustomerService(CustomerRepository customerRepo){
        this.customerRepo = customerRepo;
    }
    
    public void findCustomer(int cusID){
        customerRepo.findCustomerByID(cusID);
    }
}
