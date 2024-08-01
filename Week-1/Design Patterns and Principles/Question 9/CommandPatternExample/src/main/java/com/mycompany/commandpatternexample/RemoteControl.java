/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.commandpatternexample;

/**
 *
 * @author shari
 */
public class RemoteControl {
    Command command;
    public RemoteControl(){};
    
    public void setCommand(Command command){
        this.command = command;
    }
    
    public void executeCommand(){
        command.execute();
    }
}
