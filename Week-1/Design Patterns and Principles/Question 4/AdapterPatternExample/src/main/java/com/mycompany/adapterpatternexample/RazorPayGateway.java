/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adapterpatternexample;

/**
 *
 * @author shari
 */
public class RazorPayGateway {
    private final int paymentID;
    private final String paymentDescription;
    
    public RazorPayGateway(int Id, String description) {
        this.paymentID = Id;
        this.paymentDescription = description;
    }
    
    public void razorPayGatewayProcessor() {
        System.out.println("RazorPay: Processing Payment.......");
        System.out.println("Payment Details:");
        System.out.println("ID: " + this.getPaymentID());
        System.out.println("Description: " + this.getPaymentDescription());
    }

    /**
     * @return the paymentID
     */
    public int getPaymentID() {
        return paymentID;
    }

    /**
     * @return the paymentDescription
     */
    public String getPaymentDescription() {
        return paymentDescription;
    }
}
