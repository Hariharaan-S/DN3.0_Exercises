/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dependencyinjectionexample;

/**
 *
 * @author shari
 */
public class DependencyInjectionExample {

    public static void main(String[] args) {
        CustomerService service = new CustomerService(new CustomerRepositoryImpl());
        
        service.findCustomer(104);
    }
}
