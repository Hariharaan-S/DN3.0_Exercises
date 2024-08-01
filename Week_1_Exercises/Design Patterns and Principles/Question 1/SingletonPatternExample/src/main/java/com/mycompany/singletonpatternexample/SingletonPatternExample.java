/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.singletonpatternexample;

/**
 *
 * @author shari
 */
public class SingletonPatternExample {

    public static void main(String[] args) {
        Logger logIn = Logger.getInstance();
        System.out.print("Log in status: ");
        System.out.println(logIn.getLoggedIn());
        logIn.setLoggedIn();
        System.out.print("Instance: ");
        System.out.println(logIn.toString());
        System.out.print("Log in status: ");
        System.out.println(logIn.getLoggedIn());

        logIn = Logger.getInstance();
        System.out.print("Again trying to create new instance: ");
        System.out.println(logIn.toString());
        System.out.print("Log in status: ");
        System.out.println(logIn.getLoggedIn());
    }
}
