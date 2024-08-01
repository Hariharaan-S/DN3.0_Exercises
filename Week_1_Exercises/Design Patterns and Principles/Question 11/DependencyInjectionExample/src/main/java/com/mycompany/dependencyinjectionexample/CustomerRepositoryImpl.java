/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dependencyinjectionexample;

/**
 *
 * @author shari
 */
public class CustomerRepositoryImpl implements CustomerRepository {
    
    private static final int[]customerIDs = {101,102,103,104,105};
    private static final String[]customerNames = {"Alex","Bob","Carol","Daisy","Egua"};

    @Override
    public void findCustomerByID(int customerID) {
        boolean found = false;
        for(int index = 0; index < 5; index++){
            if(customerIDs[index] == customerID){
                System.out.println("Customer found!");
                System.out.println("The customer name is: " + customerNames[index]);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Customer Not found!");
        }
    }
    
}
