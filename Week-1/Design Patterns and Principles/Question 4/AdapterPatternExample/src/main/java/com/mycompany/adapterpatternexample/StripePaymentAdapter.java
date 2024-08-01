/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adapterpatternexample;

/**
 *
 * @author shari
 */
public class StripePaymentAdapter implements PaymentProcessor {
    private final StripeGateway stripeGateway;
    
    public StripePaymentAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment() {
        this.stripeGateway.stripePaymentProcessor();
    }
}
