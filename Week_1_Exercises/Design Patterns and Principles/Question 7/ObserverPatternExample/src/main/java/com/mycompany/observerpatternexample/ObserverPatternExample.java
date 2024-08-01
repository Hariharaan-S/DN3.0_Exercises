/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.observerpatternexample;

/**
 *
 * @author shari
 */
public class ObserverPatternExample {

    public static void main(String[] args) {
        StockMarket manageStockMarket = new StockMarket();
        Investor newInvestor1 = new Investor();
        newInvestor1.setMailID("sample@sample.com");
        manageStockMarket.register(newInvestor1, Event.SALE);

        Investor newInvestor2 = new Investor();
        newInvestor2.setMailID("sample@gmail.com");
        manageStockMarket.register(newInvestor2, Event.NEW_ITEM);

        Investor newInvestor3 = new Investor();
        newInvestor3.setMailID("sample@email.com");
        manageStockMarket.register(newInvestor3, Event.SALE);

        Investor newInvestor4 = new Investor();
        newInvestor4.setMailID("sample@yahoo.com");
        manageStockMarket.register(newInvestor4, Event.SALE);
        
        
        manageStockMarket.notifyPeople(Event.NEW_ITEM);

    }
}
