/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.strategypatternexample;

/**
 *
 * @author shari
 */
public class PaymentContext {
    private static PaymentStrategy paymentStrategy;
    
    public void processOrder() {
        paymentStrategy.collectDetails();
        paymentStrategy.pay(getTotal());
    }

    private int getTotal() {
        return 1000;
    }
    
    public void setStrategy(PaymentStrategy strategy){
        paymentStrategy = strategy;
    }
}
