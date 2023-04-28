/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


public class Book {
    private String bookID;
    private String authorFirstName;
    private String authorSurname;
    private String title;
    private String genre;
   
    
    public Book(String bookID, String authorFirstName, String authorSurname, String title, String genre) {
        this.bookID = bookID;
        this.authorFirstName = authorFirstName;
        this.authorSurname = authorSurname;
        this.title = title;
        this.genre = genre;
    }

    // Getters and setters
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String id) {
        this.bookID = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return "BOOK " + " {" +
                "\nID: " + bookID +
                "\nAUTHOR: " + authorSurname + ", " + authorFirstName +
                "\nTITLE: " + title + 
                "\nGENRE: " + genre +
                "\n}\n";
    }
}
