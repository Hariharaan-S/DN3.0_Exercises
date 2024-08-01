/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.commandpatternexample;

/**
 *
 * @author shari
 */
public class LightsOnCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Lights turned on!");
    }
    
}
