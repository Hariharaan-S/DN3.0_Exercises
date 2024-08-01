/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.builderpatternexample;

/**
 *
 * @author shari
 */
public class Computer {

    private String CPU;
    private String RAM;
    private String storage;

    private Computer(ComputerBuilder computerBuilder) {
        this.CPU = computerBuilder.CPU;
        this.RAM = computerBuilder.RAM;
        this.storage = computerBuilder.storage;
    }

    /**
     * @return the CPU
     */
    public String getCPU() {
        return CPU;
    }

    /**
     * @return the RAM
     */
    public String getRAM() {
        return RAM;
    }

    /**
     * @return the storage
     */
    public String getStorage() {
        return storage;
    }

    public static class ComputerBuilder {

        private String CPU;
        private String RAM;
        private String storage;

        public ComputerBuilder(String CPU) {
            this.CPU = CPU;
        }

        public ComputerBuilder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
