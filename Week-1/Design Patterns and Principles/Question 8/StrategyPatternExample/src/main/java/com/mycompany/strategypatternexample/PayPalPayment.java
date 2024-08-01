/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.strategypatternexample;

import java.util.Scanner;

/**
 *
 * @author shari
 */
public class PayPalPayment implements PaymentStrategy {
    
    Scanner sc = new Scanner(System.in);
   
    private static String paymentIntentID;
    private static String orderID;
    private static String customerName;

    @Override
    public void collectDetails() {
        System.out.println("Enter your payment Intent ID: ");
        paymentIntentID = sc.nextLine();
        System.out.println("Enter order ID: ");
        orderID = sc.nextLine();
        System.out.println("Enter Name: ");
        customerName = sc.nextLine();
    }

    @Override
    public void pay(int total) {
       System.out.println("A total of " + total + " is paid via PayPal. Order ID: " + orderID);
    }
    
    
    
}
