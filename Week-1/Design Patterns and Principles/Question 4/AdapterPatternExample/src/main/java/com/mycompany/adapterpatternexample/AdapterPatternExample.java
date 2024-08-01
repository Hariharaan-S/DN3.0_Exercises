/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.adapterpatternexample;

/**
 *
 * @author shari
 */
public class AdapterPatternExample {

    public static void main(String[] args) {
        RazorPayGateway razorPay = new RazorPayGateway(12112,"Payment for Grocery");
        RazorPayAdapter razorPayAdapter = new RazorPayAdapter(razorPay);
        razorPayAdapter.processPayment();
        
        StripeGateway stripePayment = new StripeGateway(12231, "Payment for Books", "Credit-Card");
        StripePaymentAdapter stripeAdapter = new StripePaymentAdapter(stripePayment);
        stripeAdapter.processPayment();
    }
}
