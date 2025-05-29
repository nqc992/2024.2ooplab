package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class Book extends Media {

    private static int nbBooks = 0; // Static variable to track the number of Book instances
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor with auto-incremented ID
    public Book(String title, String category, float cost) {
        super(++nbBooks, title, category, cost); // Increment nbBooks and assign as ID
    }

    // Add an author if not already in the list
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }

    // Remove an author from the list
    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }

    // Get the list of authors
    public ArrayList<String> getAuthorsList() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book - Title: " + getTitle() + " - Category: " + getCategory() + " - Cost: $" + getCost();
    }

    // Get the total number of Book instances created
    public static int getNbBooks() {
        return nbBooks;
    }
}
