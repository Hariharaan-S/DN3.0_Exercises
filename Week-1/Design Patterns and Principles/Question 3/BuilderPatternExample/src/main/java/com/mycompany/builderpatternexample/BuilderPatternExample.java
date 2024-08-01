/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.builderpatternexample;

/**
 *
 * @author shari
 */
public class BuilderPatternExample {

    public static void main(String[] args) {
         Computer myComputer = new Computer.ComputerBuilder("Intel i7 13th Gen @5GHz")
                .setRAM("16GB")
                .setStorage("1TB")
                .build();
         
         System.out.println("System Configuration:");
         System.out.println("1. CPU: " + myComputer.getCPU());
         System.out.println("2. RAM: " + myComputer.getRAM());
         System.out.println("3. Storage: " + myComputer.getStorage());
    }
}
