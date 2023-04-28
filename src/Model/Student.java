/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Student {

    private int studentID;
    private String studentFirstName;
    private String studentSurname;
    private String email;


    public Student(int studentID, String studentFirstName, String studentSurname, String email) {
        this.studentID = studentID;
        this.studentFirstName = studentFirstName;
        this.studentSurname = studentSurname;
        this.email = email;
    }

    // Getters and setters
    public int getStudentID() {
        return studentID;
    }
    
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }
    
    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }
    
    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student " + " {" +
                "\nID: " + studentID +
                "\nSTUDENT: " + studentSurname + ", " + studentFirstName +
                "\nEMAIL: " + email + 
                "\n}\n";
    }
}