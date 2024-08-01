/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.observerpatternexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author shari
 */
public class StockMarket implements Stock {
    private static Map<Event,List<Investor>>observers;
    
    public StockMarket(){
        observers = new HashMap<>();
        Arrays.stream(Event.values()).forEach(type -> {
            observers.put(type, new ArrayList<>());
        });
    }

    @Override
    public void register(Investor investor, Event type) {
        observers.get(type).add(investor);
    }

    @Override
    public void deregiter(Investor investor, Event type) {
        observers.get(type).remove(investor);
    }

    @Override
    public void notifyPeople(Event type) {
        observers.get(type).forEach(observer -> {
            System.out.println("Observer: " + observer.getMailID());
            observer.update();
        });
    }
}
