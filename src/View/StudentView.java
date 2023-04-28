/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.StudentController;
import Model.Student;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private StudentController controller;
    private Scanner scanner;

    public StudentView(StudentController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void runStudentView() {
        controller.readStudentsFromCSV("STUDENTS.csv");
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:"
                    + "\n1. Sort students by ID"
                    + "\n2. Sort students by name"
                    + "\n3. Search students by ID"
                    + "\n4. Search students by full name"
                    + "\n5. Return to Menu");
            System.out.print("\n>> ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        printStudentsSortedByID();
                        break;
                    case 2:
                        printStudentsSortedByName();
                        break;
                    case 3:
                        printStudentsSearchedByID();
                        break;
                    case 4:
                        printStudentBySeachedByName();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear input buffer
            }
        }
    }
    
    private void printStudentsSortedByID() {
        controller.sortStudentsByID();
        System.out.println("Students sorted by ID." + "\n");
        printStudents(controller.getStudents());
    }
    
    private void printStudentsSortedByName() {
        controller.sortStudentsByFullName();
        System.out.println("Students sorted by name." + "\n");
        printStudents(controller.getStudents());
    }
    
    private void printStudentsSearchedByID() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        List<Student> result1 = controller.searchStudentsByID(studentID);
        if (result1.isEmpty()) {
            System.out.println("No students found with ID: " + studentID);
        } 
        else {
            System.out.println("Students found with ID: " + studentID + "\n");
            printStudents(result1);
        } 
    }
    
    private void printStudentBySeachedByName() {
        System.out.print("Enter student full name: ");
        String fullName = scanner.nextLine();
        List<Student> result2 = controller.searchStudentsByFullName(fullName);
        if (result2.isEmpty()) {
            System.out.println("No students found with full name: " + fullName);
        } 
        else {
            System.out.println("Students found with full name: " + fullName + "\n");
            printStudents(result2);
        }
    }
    
    private void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
