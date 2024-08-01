/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.decoratorpatternexample;

/**
 *
 * @author shari
 */
public abstract class NotifierDecorator implements Notifier {

    private final Notifier notifier;

    NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void sendMessage(String message) {
        notifier.sendMessage(message);
    }

}
