/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxypatternexample;

/**
 *
 * @author shari
 */
public class RealImage implements Image {
    private final String fileName;
    public RealImage(String filename){
        this.fileName = filename;
        loadImageFromRemoteServer();
    }

    @Override
    public void display() {
        System.out.println("Displayed Image name: " + this.fileName);
    }

    private void loadImageFromRemoteServer() {
        System.out.println("Loading Image from Remote Server: " + this.fileName);
    }
    
}
