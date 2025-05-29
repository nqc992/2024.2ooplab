package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;
import java.util.Collections;

import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            System.out.println("The product is already in the cart!");
        } else {
            itemsOrdered.add(media);
            System.out.println("Added: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("Removed product: " + media.getTitle());
        } else {
            System.out.println("This product does not exist in the cart!");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void printCart() {
        System.out.println("********** CART **********");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            System.out.println((i + 1) + ". " + media.toString());
        }
        System.out.printf("Total cost: %.2f\n", totalCost());
        System.out.println("***************************");
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null; // not found
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null; // not found
    }

    public void clear() {
        itemsOrdered.clear();
    }
}
