/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adapterpatternexample;

/**
 *
 * @author shari
 */
public class RazorPayAdapter implements PaymentProcessor {
    private final RazorPayGateway razorPay;
    
    public RazorPayAdapter(RazorPayGateway razorPay){
        this.razorPay = razorPay;
    }

    @Override
    public void processPayment() {
        this.razorPay.razorPayGatewayProcessor();
    }
    
}
