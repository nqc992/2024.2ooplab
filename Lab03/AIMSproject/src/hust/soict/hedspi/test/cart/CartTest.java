package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King",
                "Animation", 19.95f, 87, "Roger Allers");
        cart.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars",
                "Science Fiction", 24.95f, 87, "George Lucas");
        cart.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladin",
                "Animation", 18.99f, 90, "John Musker");
        cart.addMedia(dvd3);

        // Test print method
        cart.print();

        // Test search by ID
        System.out.println("Search for ID 2: " + cart.searchByTitle("Star Wars"));

        // Test search by title
        System.out.println("Search for 'lion': " + cart.searchByTitle("lion"));
    }
}