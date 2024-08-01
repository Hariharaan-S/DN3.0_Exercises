package com.mycompany.employeemanagementsystem;

import java.util.Scanner;

/**
 *
 * @author shari
 */
public class EmployeeManagementSystem {

    public static void main(String[] args) {
        System.out.println("================= EMPLOYEE MANAGEMENT SYSTEM ====================");
        EmployeeController manageEmployee = new EmployeeController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("================== MENU ===================");
            System.out.print("1. Add new employee\n2. Search Employee\n3. Delete Employee\n4. Display Employees\n5. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Employee ID: ");
                    int empID = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    System.out.print("Enter Employee Name: ");
                    String empName = sc.nextLine();
                    System.out.println();
                    System.out.print("Enter Employee Position: ");
                    String empPosition = sc.nextLine();
                    System.out.println();
                    System.out.print("Enter Employee Salary: ");
                    int empSalary = sc.nextInt();

                    manageEmployee.addEmployee(empID, empName, empPosition, empSalary);
                    System.out.println("Employee added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter Employee ID: ");
                    int empID = sc.nextInt();
                    sc.nextLine();
                    Employee foundEmployee = manageEmployee.searchEmployee(empID);
                    manageEmployee.displayEmployee(foundEmployee);
                }

                case 3 -> {
                    System.out.print("Enter Employee ID: ");
                    int empID = sc.nextInt();
                    sc.nextLine();
                    manageEmployee.deleteEmployee(empID);
                }

                case 4 -> {
                    System.out.println("The working employees are:");
                    manageEmployee.traverseEmployee();
                }
                default -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
            }
        }
    }
}
