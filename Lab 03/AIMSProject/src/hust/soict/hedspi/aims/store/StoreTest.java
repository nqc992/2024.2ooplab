package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King",
                "Animation", 19.95f, 87, "Roger Allers");
        store.addMedia(dvd1);

        Book book1 = new Book(2, "Aladin", "Animation", 18.99f);
        book1.addAuthor("John Smith");
        store.addMedia(book1);

        CompactDisc cd1 = new CompactDisc(3, "Thriller", "Pop", 15.99f,
                "Michael Jackson", "Quincy Jones");
        Track track1 = new Track("Thriller", 357);
        Track track2 = new Track("Beat It", 258);
        cd1.addTrack(track1);
        cd1.addTrack(track2);
        store.addMedia(cd1);

        // Display store
        store.displayStore();

        // Test remove
        store.removeMedia(dvd1);
        store.displayStore();
    }
}