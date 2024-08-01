/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employeemanagementsystem;

/**
 *
 * @author shari
 */
public class Employee implements Comparable<Employee> {
    private int employeeID;
    private String employeeName;
    private String position;
    private int salary;

    /**
     * @return the emplyeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param emplyeeID the emplyeeID to set
     */
    public void setEmployeeID(int emplyeeID) {
        this.employeeID = emplyeeID;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.getEmployeeID(), o.getEmployeeID());
    }
}
