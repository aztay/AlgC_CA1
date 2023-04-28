/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.BookController;
import Controller.StudentController;
import java.util.Scanner;

public class LibraryView {
    private Scanner scanner;

    public LibraryView() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n-------------------Welcome to CCT Library!-------------------");
            System.out.println("\nSelect an option to manage"
                    + "\n1. Books"
                    + "\n2. Students"
                    + "\n3. Borrowings"
                    + "\n4. Exit");
            System.out.print("\n>> ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        BookController bookController = new BookController();
                        BookView bookView = new BookView(bookController);
                        bookView.runBookView();
                        break;
                    case 2:
                        StudentController studentController = new StudentController();
                        StudentView studentView = new StudentView(studentController);
                        studentView.runStudentView();
                        break;
                    case 3:
                        // nothing yet
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the invalid input to avoid infinite loop
            }
        }

        scanner.close();
    }
}
