/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskmanagementsystem;

import java.util.Scanner;

/**
 *
 * @author shari
 */
public class TaskManagementSystem {

    public static void main(String[] args) {
        System.out.println("================= TASK MANAGEMENT SYSTEM ====================");
        TaskController manageTask = new TaskController();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("================== MENU ===================");
            System.out.print("1. Add new Task\n2. Search Task\n3. Delete Task\n4. Display task\n5. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();  
            sc.nextLine();
            switch(choice){
                case 1 -> {
                    System.out.println("Enter task ID: ");
                    int taskID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter task name: ");
                    String taskName = sc.nextLine();
                    
                    System.out.println("Enter task status: ");
                    String taskStatus = sc.nextLine();
                    System.out.println("Enter quantity: ");
                    manageTask.addTask(taskID, taskName, taskStatus);
                    System.out.println("Task added successfully!");
                    break;
                }
                case 2 -> {
                    System.out.println("Enter task ID: ");
                    int taskID = sc.nextInt();
                    manageTask.searchTask(taskID);
                    break;
                }
                
                case 3 -> {
                    System.out.println("Enter task ID: ");
                    int taskID = sc.nextInt();
                    manageTask.deleteTask(taskID);
                    break;
                }
                case 4 -> {
                    System.out.println("The tasks are:");
                    manageTask.displayTask();
                    
                    break;
                }
                default -> {
                    break;
                }
            }
            if(choice == 5){
                break;
            }
        }
    }
}
