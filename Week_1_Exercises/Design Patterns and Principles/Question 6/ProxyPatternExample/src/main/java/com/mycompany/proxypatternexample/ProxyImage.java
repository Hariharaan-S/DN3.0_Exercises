/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxypatternexample;

/**
 *
 * @author shari
 */
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    
    public ProxyImage(String filename){
        this.fileName = filename;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(this.fileName);
        }
        realImage.display();
    }
    
}
