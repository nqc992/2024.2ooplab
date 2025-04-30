package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class Book extends Media {

    private static int nbBooks = 0; // Biến tĩnh để theo dõi số lượng Book
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor sử dụng id tự động tăng
    public Book(String title, String category, float cost) {
        super(++nbBooks, title, category, cost); // Tăng nbBooks và cấp ID
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }

    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }

    public ArrayList<String> getAuthorsList() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book - Title: " + getTitle() + " - Category: " + getCategory() + " - Cost: $" + getCost();
    }

    public static int getNbBooks() {
        return nbBooks;
    }
}
