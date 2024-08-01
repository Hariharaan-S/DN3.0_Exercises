/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanagementsystem;

import java.util.LinkedList;

/**
 *
 * @author shari
 */
public class TaskController {

    private static LinkedList<Task> taskList = new LinkedList<>();
    private static Task head = null;
    private static Task tail = head;

    public void addTask(int taskID, String taskName, String status) {
        Task newTask = new Task();
        newTask.setTaskID(taskID);
        newTask.setTaskName(taskName);
        newTask.setStatus(status);
        newTask.setPointerToNexTask(null);
        if (head == null) {
            head = tail = newTask;
        } else {
            tail.setPointerToNexTask(newTask);
            tail = newTask;
        }
    }

    public void searchTask(int taskID) {
        Task temp = head;
        while (temp != null) {
            if (temp.getTaskID() == taskID) {
                System.out.println("Task Found!");
                System.out.println("Task ID: " + temp.getTaskID());
                System.out.println("Task Name: " + temp.getTaskName());
                System.out.println("Task Status: " + temp.getStatus());
                break;
            }
            temp = temp.getPointerToNexTask();
        }
        if (temp == null) {
            System.out.println("Task Not Found!");
        }
    }

    public void displayTask() {
        Task temp = head;
        while (temp != null) {
            System.out.println("Task ID: " + temp.getTaskID());
            System.out.println("Task Name: " + temp.getTaskName());
            System.out.println("Task Status: " + temp.getStatus());
            System.out.println();
            temp = temp.getPointerToNexTask();
        }
    }

    public void deleteTask(int taskID) {
        Task temp = head;
        Task ptemp = temp;
        while (temp != null) {
            if (temp.getTaskID() == taskID) {
                break;
            }
            ptemp = temp;
            temp = temp.getPointerToNexTask();
        }
        if (temp == null) {
            System.out.println("No tasks found.");
        } else {
            if (temp == head) {
                System.out.println(temp.getTaskName() + " task is deleted... ");
                head = temp.getPointerToNexTask();
            } else if (temp == tail) {
                System.out.println(temp.getTaskName() + " task is deleted... ");
                tail = ptemp;
            } else {
                System.out.println(temp.getTaskName() + " task is deleted... ");
                ptemp.setPointerToNexTask(temp.getPointerToNexTask());
            }
        }
    }

}
