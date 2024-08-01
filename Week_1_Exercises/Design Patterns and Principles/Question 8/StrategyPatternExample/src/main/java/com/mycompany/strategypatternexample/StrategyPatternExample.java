/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.strategypatternexample;

/**
 *
 * @author shari
 */
public class StrategyPatternExample {

    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();
        paymentContext.setStrategy(new CreditCardPayment());
        
        paymentContext.processOrder();
    }
}
