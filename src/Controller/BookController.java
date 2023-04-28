/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookController {
    private List<Book> books; // list to store books

    public BookController() {
        books = new ArrayList<>(); // instantiate the books list as an empty ArrayList
    }

    // read books data from a CSV file and add them to the books list
    public void readBooksFromCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // skip header line
            while ((line = reader.readLine()) != null) { // read each line in file
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // split by comma, but ignore commas within double quotes;
                String bookID = fields[0].trim();
                String authorFirstName = fields[1].trim();
                String authorSurname = fields[2].trim();
                String title = fields[3].trim();
                String genre = fields[4].trim();

                Book book = new Book(bookID, authorFirstName, authorSurname, title, genre);// create a new Book object using the file fields
                books.add(book);
            }
        } catch (IOException e) {// catch any exceptions while reading the file
            System.out.println("Error reading books from CSV file: " + e.getMessage());
        }
    }
    
    // sort the books list by author name (last name, then first name)
    public void sortBooksByAuthorName() {
        mergeSort(books, (b1, b2) -> {
            int result = b1.getAuthorSurname().compareToIgnoreCase(b2.getAuthorSurname()); // compare author's surnames
            if (result == 0) {// if the surnames match
                result = b1.getAuthorFirstName().compareToIgnoreCase(b2.getAuthorFirstName()); // compare author's first names
            }
            return result; // return sorted list by author
        }
        );
    }

    // sort the books list by title
    public void sortBooksByTitle() {
        mergeSort(books, (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
    }

    // search for books with a given book title
    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>(); 
        for (Book book : books) { // iterate through book list
            // Check if the title of the book matches the given title (case-insensitive)
            if (book.getTitle().equalsIgnoreCase(title)) { // compare input to titles (case-insensitive)
                result.add(book);// add the book to the "result" list
            }
        }
        return result; // return list of matching books to title
    }
    
     
    // FOR BORROWINGS CONTROLLER CLASS
    // search for a single book in the "books" list matching a given title
    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book; // return the book if there is a match
            }
        }
        return null; // if no matches found, return null
    }

    // returns the "books" list
    public List<Book> getBooks() {
        return books;
    }
    
    // private helper method that performs a merge sort on a given list using a given comparator
    private static <T> void mergeSort(List<T> list, java.util.Comparator<T> comparator) {
        int size = list.size(); 
        if (size < 2) { // if list has less than two elements, it is already sorted
            return; // if then return
        }

        
        int mid = size / 2;// divide list into two sublists
        List<T> leftList = new ArrayList<>(list.subList(0, mid));
        List<T> rightList = new ArrayList<>(list.subList(mid, size));

        // sort each sublist using the same comparator, recursively
        mergeSort(leftList, comparator);
        mergeSort(rightList, comparator);

        // merge the two sorted sublists back into the original list
        int i = 0, j = 0, k = 0;
        while (i < leftList.size() && j < rightList.size()) { // compare first elements of each sublist using the comparator
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) { // add it to the original list if left element is smaller
                list.set(k++, leftList.get(i++));
            } else { // add it to the original list if right element is smaller
                list.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) { // add remaining elements from the left sublist to the original list
            list.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {// now add remaining elements from the right sublist
            list.set(k++, rightList.get(j++));
        }
    }
}