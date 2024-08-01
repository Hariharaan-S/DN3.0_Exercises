/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanagementsystem;

/**
 *
 * @author shari
 */
public class Task {
    private int taskID;
    private String taskName;
    private String status;
    private Task pointerToNexTask;

    /**
     * @return the taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the pointerToNexTask
     */
    public Task getPointerToNexTask() {
        return pointerToNexTask;
    }

    /**
     * @param pointerToNexTask the pointerToNexTask to set
     */
    public void setPointerToNexTask(Task pointerToNexTask) {
        this.pointerToNexTask = pointerToNexTask;
    }
}
