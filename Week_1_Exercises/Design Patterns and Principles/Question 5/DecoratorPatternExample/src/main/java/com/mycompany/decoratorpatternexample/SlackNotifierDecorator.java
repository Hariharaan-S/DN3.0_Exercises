/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.decoratorpatternexample;

/**
 *
 * @author shari
 */
public class SlackNotifierDecorator extends NotifierDecorator {
    
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    
    @Override
    public void sendMessage(String message){
        super.sendMessage(message);
        System.out.println("Sending message via Slack... " + message);
    }
    
}
