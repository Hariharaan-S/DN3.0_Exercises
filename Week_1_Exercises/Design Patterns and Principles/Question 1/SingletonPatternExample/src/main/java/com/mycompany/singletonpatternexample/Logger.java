/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.singletonpatternexample;

/**
 *
 * @author shari
 */
public class Logger {

    private static volatile Logger loggerInstance;
    private boolean isLoggedIn = false;

    private Logger() {}
    
    public boolean getLoggedIn() {
        return this.isLoggedIn;
    }
    
    public void setLoggedIn() {
        this.isLoggedIn = true;
    }

    public static Logger getInstance() {
        Logger logInstance = loggerInstance;
        if(logInstance == null){
            synchronized (Logger.class) {
                if (logInstance == null) {
                    loggerInstance = logInstance = new Logger();
                }
            } 
        }
        return logInstance;
    }

}
