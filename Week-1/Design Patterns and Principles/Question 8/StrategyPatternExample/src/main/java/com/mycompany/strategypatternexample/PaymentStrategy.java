/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.strategypatternexample;

/**
 *
 * @author shari
 */
public interface PaymentStrategy {
    public void collectDetails();
    public void pay(int total);
    
}
