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
public class CreditCardPayment implements PaymentStrategy {
    
    Scanner sc = new Scanner(System.in); 
    private static int creditCardNumber;
    private static String cardHolderName;
    private static int CVV;
    

    @Override
    public void collectDetails() {
        System.out.println("Enter your card number: ");
        creditCardNumber = sc.nextInt(); sc.nextLine();
        System.out.println("Enter card holer name: ");
        cardHolderName = sc.nextLine();
        System.out.println("Enter CVV: ");
        CVV = sc.nextInt();
    }

    @Override
    public void pay(int total) {
        System.out.println("A total of " + total + " is paid via card number: " + creditCardNumber);
    }
    
}
