/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mvcpatternexample;

/**
 *
 * @author shari
 */
public class MVCPatternExample {

    public static void main(String[] args) {
        Student student = new Student();
        StudentView studentView = new StudentView();
        StudentController controller = new StudentController(student,studentView);
        
        controller.setStudentName("Hari");
        controller.setStudentID(1010101);
        controller.setStudentGrade('O');
        
        controller.showStudent();
        
    }
}
