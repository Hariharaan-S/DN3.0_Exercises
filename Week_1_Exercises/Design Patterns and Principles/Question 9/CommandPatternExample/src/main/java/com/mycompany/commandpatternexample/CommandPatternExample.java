/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.commandpatternexample;

/**
 *
 * @author shari
 */
public class CommandPatternExample {

    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        remote.setCommand(new LightsOnCommand());
        remote.executeCommand();
        
        remote.setCommand(new LightsOffCommand());
        remote.executeCommand();
    }
}
