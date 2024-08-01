/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.decoratorpatternexample;

/**
 *
 * @author shari
 */
public class EmailNotifier implements Notifier {

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending message via email... " + message);
    }
     
}
