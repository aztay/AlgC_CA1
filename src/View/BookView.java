/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;


import Model.Book;
import Controller.BookController;
import java.util.List;
import java.util.Scanner;

public class BookView {
    private BookController controller;
    private Scanner scanner;

    public BookView(BookController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void runBookView() {
        controller.readBooksFromCSV("BOOKS.csv");
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:"
                    + "\n1. Sort books by author's full name"
                    + "\n2. Sort books by title"
                    + "\n3. Search books by author's full name"
                    + "\n4. Search books by title"
                    + "\n5. Back to Menu");
            System.out.print("\n>> ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        printAuthorSortedByName();
                        break;
                    case 2:
                        printAuthorSortedByTitle();
                        break;
                    case 3:
                        printAuthorSearchedByName();
                        break;
                    case 4:
                        printBookBySeachedTitle();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void printAuthorSortedByName() {
        controller.sortBooksByAuthorName();
        System.out.println("Books sorted by author's full name." + "\n");
        printBooks(controller.getBooks());
    }
    
    private void printAuthorSortedByTitle() {
        controller.sortBooksByTitle();
        System.out.println("Books sorted by title." + "\n");
        printBooks(controller.getBooks());
    }
    
    private void printAuthorSearchedByName() {
        System.out.print("Enter author's full name: ");
        String authorName = scanner.nextLine();
        List<Book> result1 = controller.searchBooksByAuthorName(authorName);
        if (result1.isEmpty()) {
            System.out.println("No books found with author's full name: " + authorName);
        } 
        else {
            System.out.println("Books found with author's full name: " + authorName + "\n");
            printBooks(result1);
        }   
    }
    
    private void printBookBySeachedTitle() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        List<Book> result2 = controller.searchBooksByTitle(title);
        if (result2.isEmpty()) {
            System.out.println("No books found with title: " + title);
        } 
        else {
            System.out.println("Books found with title: " + title + "\n");
            printBooks(result2);
        }
    }
    
    private void printBooks(List<Book> books){
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
