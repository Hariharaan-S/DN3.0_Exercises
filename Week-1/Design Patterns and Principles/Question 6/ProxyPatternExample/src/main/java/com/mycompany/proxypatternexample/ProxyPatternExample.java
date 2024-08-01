/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proxypatternexample;

/**
 *
 * @author shari
 */
public class ProxyPatternExample {

    public static void main(String[] args) {
        ProxyImage proxyImage = new ProxyImage("Myimage.jpeg");
        proxyImage.display();
        
        proxyImage.display();
    }
}
