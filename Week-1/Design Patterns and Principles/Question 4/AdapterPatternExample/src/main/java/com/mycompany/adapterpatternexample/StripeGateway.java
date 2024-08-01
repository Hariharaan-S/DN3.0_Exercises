/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adapterpatternexample;

/**
 *
 * @author shari
 */
public class StripeGateway {
    private final int paymentID;
    private final String paymenyIntent;
    private final String methodOfPayment;
    
    public StripeGateway(int ID, String paymentIntent, String method){
        this.paymentID = ID;
        this.paymenyIntent = paymentIntent;
        this.methodOfPayment = method;
    }
    
    public void stripePaymentProcessor(){
        System.out.println("Stripe Payment: Processing payment...");
        System.out.println("Payment Details: ");
        System.out.println("Payment ID: " + this.getPaymentID());
        System.out.println("Payment Intent: " + this.getPaymenyIntent());
        System.out.println("Method of payment: "+this.getMethodOfPayment());
    }

    /**
     * @return the paymentID
     */
    public int getPaymentID() {
        return paymentID;
    }

    /**
     * @return the paymenyIntent
     */
    public String getPaymenyIntent() {
        return paymenyIntent;
    }

    /**
     * @return the methodOfPayment
     */
    public String getMethodOfPayment() {
        return methodOfPayment;
    }
}
