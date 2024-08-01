/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.observerpatternexample;

/**
 *
 * @author shari
 */
public class Investor implements Observer {
    private String mailID;

    /**
     * @return the mailID
     */
    public String getMailID() {
        return mailID;
    }

    /**
     * @param mailID the mailID to set
     */
    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    @Override
    public void update() {
        System.out.println("New Item arrived!");
    }
    
}
