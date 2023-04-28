
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controller;

import Model.Book;
import Model.Borrowings;
import Model.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class BorrowingsController {

    private Queue<Student> waitingList = new LinkedList<>();
    private Queue<Borrowings> borrowings = new LinkedList<>();

    public void borrowBook(String title, String studentFullName, BookController bookController, StudentController studentController) {
        Book book = bookController.getBookByTitle(title);
        Student student = studentController.getStudentByFullName(studentFullName);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (student == null) {
            System.out.println("Student not found.");
        } else {
            // Check if the book is already borrowed and not returned
            boolean alreadyBorrowed = false;
            for (Borrowings borrowing : borrowings) {
                if (borrowing.getBook().equals(book) && borrowing.getReturnDate() == null) {
                    alreadyBorrowed = true;
                    break;
                }
            }
            if (alreadyBorrowed) {
                // Add the student to the waiting list
                waitingList.add(student);
                System.out.println("Book " + book.getTitle() + " is already borrowed and not returned. " + student.getStudentFirstName() + " " + student.getStudentSurname() + " added to waiting list.");
                saveWaitingListToFile();
            } else {
                // Borrow the book
                LocalDate borrowDate = LocalDate.now();
                LocalDate returnDate = borrowDate.plusDays(14);
                Borrowings borrowing = new Borrowings(book, student, borrowDate, returnDate);
                borrowings.add(borrowing);
                System.out.println("Book " + book.getTitle() + " borrowed by " + student.getStudentFirstName() + " " + student.getStudentSurname());
                System.out.println("Added to borrowings queue.");
                saveBorrowingsToFile();
            }
        }
    }

    public void returnBook(String title, BookController bookController, StudentController studentController) {
        loadBorrowingsFromFile(bookController, studentController); // load current borrowings
        Book book = bookController.getBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            Borrowings borrowedBook = null;
            for (Borrowings borrowing : borrowings) {
                if (borrowing.getBook().equals(book)) {
                    borrowedBook = borrowing;
                    break;
                }
            }
            if (borrowedBook == null) {
                System.out.println("Book " + book.getTitle() + " is not borrowed.");
            } else {
                Student student = borrowedBook.getStudent();
                LocalDate returnDate = LocalDate.now();
                borrowedBook.setReturnDate(returnDate);
                System.out.println("Book " + book.getTitle() + " returned by " + student.getStudentFirstName() + " " + student.getStudentSurname());
                saveBorrowingsToFile(); // save updated borrowings list to file
                if (!waitingList.isEmpty()) {
                    Student nextInLine = waitingList.poll();
                    borrowBook(book.getTitle(), nextInLine.getStudentFirstName() + " " + nextInLine.getStudentSurname(), bookController, studentController);
                }
            }
        }
    }

    public void getBooksByStudentName(String studentFullName) {
        for (Borrowings borrowing : borrowings) {
            if ((borrowing.getStudent().getStudentFirstName() + " " + borrowing.getStudent().getStudentSurname()).equals(studentFullName)) {
                System.out.println(borrowing.getBook().getTitle());
            }
        }
    }

    // Method to save borrowings to BORROWINGS.csv file
    public void saveBorrowingsToFile() {
        try (FileWriter writer = new FileWriter("BORROWINGS.csv")) {
            for (Borrowings borrowing : borrowings) {
                writer.write(borrowing.getBook().getTitle() + "," 
                        + borrowing.getStudent().getStudentFirstName() + " " + borrowing.getStudent().getStudentFirstName() +  "," 
                        + borrowing.getBorrowDate() + "," 
                        + borrowing.getReturnDate() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to save borrowings to file.");
        }
    }

    // Method to save waiting list to WAITING_LIST.csv file
    private void saveWaitingListToFile() {
        try (FileWriter writer = new FileWriter("WAITING_LIST.csv")) {
            for (Student student : waitingList) {
                writer.write(student.getStudentFirstName() + " " + student.getStudentSurname() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to save waiting list to file.");
        }
    }

    // Method to load waiting list from WAITING_LIST.csv file
    public void loadWaitingListFromFile(StudentController studentController) {
        try (BufferedReader reader = new BufferedReader(new FileReader("WAITING_LIST.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String studentFullName = line.trim();
                Student student = studentController.getStudentByFullName(studentFullName);
                waitingList.add(student);
            }
        } catch (IOException e) {
            System.out.println("Failed to load waiting list from file.");
        }
    }
    
    public void displayNextInLine() {
        if (waitingList.isEmpty()) {
            System.out.println("No students are waiting.");
        } else {
            Student nextInLine = waitingList.peek();
            System.out.println("Next student in line: " + nextInLine.getStudentFirstName() + " " + nextInLine.getStudentSurname());
        }
    }

    
    public void loadBorrowingsFromFile(BookController bookController, StudentController studentController) {
        try (BufferedReader reader = new BufferedReader(new FileReader("BORROWINGS.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String bookTitle = fields[0];
                String studentFullName = fields[1];
                LocalDate borrowDate = LocalDate.parse(fields[2]);
                LocalDate returnDate = null;
                if (!fields[3].equals("null")) {
                    returnDate = LocalDate.parse(fields[3]);
                }
                Book book = bookController.getBookByTitle(bookTitle);
                Student student = studentController.getStudentByFullName(studentFullName);
                if (book != null && student != null) {
                    Borrowings borrowing = new Borrowings(book, student, borrowDate, returnDate);
                    borrowings.add(borrowing);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load borrowings from file.");
        }
    }
}