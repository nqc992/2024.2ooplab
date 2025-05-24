package hust.soict.hedspi.aims.store;

import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (itemsInStore.contains(media)) {
            System.out.println("San pham da ton tai trong cua hang!");
        } else {
            itemsInStore.add(media);
            System.out.println("Them thanh cong san pham co ten: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("Da xoa: " + media.getTitle());
        } else {
            System.out.println("San pham khong ton tai!");
        }
    }

    public void printStore() {
        System.out.println("********** STORE **********");
        for (int i = 0; i < itemsInStore.size(); i++) {
            Media media = itemsInStore.get(i);
            System.out.println((i + 1) + ". " + media.toString());
        }
        System.out.println("***************************");
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
    
    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) { 
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null; // không tìm thấy
    }
    public Media searchById(int id) {
        for (Media media : itemsInStore) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null; // không tìm thấy
    }
    
    
}
