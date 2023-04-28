/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.BookController;
import Controller.BorrowingsController;
import Controller.StudentController;
import java.util.Scanner;

public class BorrowingsView {

    private Scanner scanner;
    private BorrowingsController controller;
    private BookController bookController;
    private StudentController studentController;

    public BorrowingsView(BorrowingsController controller) {
        bookController = new BookController();
        studentController = new StudentController();
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void runBorrowingsView() {
        bookController.readBooksFromCSV("BOOKS.csv");
        studentController.readStudentsFromCSV("STUDENTS.csv");
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\nChoose an option:"
                    + "\n1. Borrow a book"
                    + "\n2. Return a book"
                    + "\n3. Next student waiting for a book"
                    + "\n4. List books borrowed by a student"
                    + "\n5. Save borrowings to file"
                    + "\n6. Quit");
            System.out.print("\n>> ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    saveBorrowingsToFile();
                    break;
                case 6:
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
    public void borrowBook() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter student full name:");
        String studentFullName = scanner.nextLine();
        controller.borrowBook(title, studentFullName, bookController, studentController);
    }

    public void returnBook() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        controller.returnBook(title, bookController, studentController);
    }
    
/* HAS TO BE DEBUGGED
    public void displayNextInLine() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        controller.displayNextInLine(title, bookController);
    }
*/
    
    public void listBooks() {
        System.out.println("Enter student full name:");
        String studentFullName = scanner.nextLine();
        controller.getBooksByStudentName(studentFullName);
    }

    public void saveBorrowingsToFile() {
        controller.saveBorrowingsToFile();
    }

    public void loadBorrowingsFromFile() {
        controller.loadBorrowingsFromFile(bookController, studentController);
    }
/*
    public void addToWaitingList() {
        System.out.println("Enter student full name:");
        String studentFullName = scanner.nextLine();
        controller.addToWaitingList(studentFullName, studentController);
    }
*/
}