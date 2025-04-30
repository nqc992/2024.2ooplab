package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;
import java.util.Collections;

import hust.soict.hedspi.aims.media.Media;

public class Cart {
	private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
	
    public void addMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            System.out.println("San pham da co san trong gio hang!");
        } else {
            itemsOrdered.add(media);
            System.out.println("Added: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("Da xoa san pham: " + media.getTitle());
        } else {
            System.out.println("Khong ton tai san pham nay!");
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

    public ArrayList<Media> getItemsOrdered() {
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
        return null; // không tìm thấy
    }
    
    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null; // không tìm thấy
    }
    public void clear() {
        itemsOrdered.clear();
    }

    
}
