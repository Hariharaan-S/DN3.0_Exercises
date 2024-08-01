/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.decoratorpatternexample;

/**
 *
 * @author shari
 */
public class DecoratorPatternExample {

    public static void main(String[] args) {
        Notifier notifier = new SMSNotifierDecorator(new SlackNotifierDecorator(new EmailNotifier()));
        notifier.sendMessage("Hello World!");
    }
}
