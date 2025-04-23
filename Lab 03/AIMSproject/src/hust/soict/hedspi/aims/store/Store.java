package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("Added to store: " + media.getTitle());
        } else {
            System.out.println("Media already in store: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Removed from store: " + media.getTitle());
        } else {
            System.out.println("Media not found in store: " + media.getTitle());
        }
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void displayStore() {
        System.out.println("***********************STORE***********************");
        System.out.println("Available Media:");
        for (Media media : itemsInStore) {
            System.out.println(media);
        }
        System.out.println("***************************************************");
    }

    public List<Media> getItemsInStore() {
        return itemsInStore;
    }
}