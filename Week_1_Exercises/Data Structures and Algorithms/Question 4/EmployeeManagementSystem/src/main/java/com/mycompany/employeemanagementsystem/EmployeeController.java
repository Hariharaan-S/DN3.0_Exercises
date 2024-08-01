package com.mycompany.employeemanagementsystem;

import java.util.Arrays;

/**
 *
 * @author shari
 */
public class EmployeeController {

    private static int numberOfEmployees = 0;
    private static final Employee[] employeeList = new Employee[5];

    public void addEmployee(int empID, String name, String position, int salary) {
        if (numberOfEmployees == 5) {
            System.out.println("Cannot add new Employee... Fire someone..");
            return;
        }
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeID(empID);
        newEmployee.setEmployeeName(name);
        newEmployee.setPosition(position);
        newEmployee.setSalary(salary);

        for (int i = 0; i < employeeList.length; i++) {
            if (employeeList[i] == null) {
                employeeList[i] = newEmployee;
                break;
            }
        }
        numberOfEmployees++;
    }

    public void displayEmployee(Employee employee) {
        if (employee != null) {
            System.out.println("Employee ID: " + employee.getEmployeeID());
            System.out.println("Employee Name: " + employee.getEmployeeName());
            System.out.println("Employee Position: " + employee.getPosition());
            System.out.println("Employee Salary: " + employee.getSalary());
        } else {
            System.out.println("Employee not found.");
        }
    }

    public Employee searchEmployee(int empID) {
        Employee[] nonNullEmployees = Arrays.stream(employeeList)
                .filter(employee -> employee != null)
                .toArray(Employee[]::new);

        Arrays.sort(nonNullEmployees);
        int left = 0;
        int right = nonNullEmployees.length - 1;
        while (left <= right) {
            int mid = (left + (right - left)) / 2;
            if (nonNullEmployees[mid].getEmployeeID() == empID) {
                return nonNullEmployees[mid];
            } else if (nonNullEmployees[mid].getEmployeeID() < empID) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public void deleteEmployee(int empID) {
        for (int i = 0; i < employeeList.length; i++) {
            if (employeeList[i] != null && employeeList[i].getEmployeeID() == empID) {
                System.out.println("Employee fired.. The employee is: " + employeeList[i].getEmployeeName());
                employeeList[i] = null;
                numberOfEmployees--;
                break;
            }
        }
    }

    public void traverseEmployee() {
        for (Employee employee : employeeList) {
            if (employee != null) {
                this.displayEmployee(employee);
            }
        }
    }
}
