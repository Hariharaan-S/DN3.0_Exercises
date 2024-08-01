/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvcpatternexample;

/**
 *
 * @author shari
 */
public class StudentController {

    private Student student;
    private StudentView viewStudent;

    public StudentController(Student student, StudentView studentView) {
        this.student = student;
        this.viewStudent = studentView;
    }
    
    public void setStudentName(String studentName) {
        this.student.setStudentName(studentName);
    }
    
    public void setStudentID(int studentID){
        this.student.setStudentID(studentID);
    }
    
    public void setStudentGrade(char grade){
        this.student.setStudentGrade(grade);
    }
    
    public void showStudent(){
        viewStudent.displayStudent(this.student.getStudentName(), this.student.getStudentID(), this.student.getStudentGrade());
    }
}
