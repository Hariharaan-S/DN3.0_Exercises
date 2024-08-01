/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.observerpatternexample;

/**
 *
 * @author shari
 */
public interface Stock {

    public void register(Investor investor, Event type);

    public void deregiter(Investor investor, Event type);

    public void notifyPeople(Event type);
}
