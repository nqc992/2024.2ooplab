package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {
        public static void main(String[] args) {
                Cart cart = new Cart();

                // Sample media items
                DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
                DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
                DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);

                // Add media items to cart
                cart.addMedia(new Book("Java Programming", "Programming", 20.5f));
                cart.addMedia(new DigitalVideoDisc("Avatar", "Sci-fi", "James Cameron", 90, 19.9f));
                cart.addMedia(new Book("Java Programming", "Programming", 25.0f));
                cart.addMedia(dvd3);
                cart.addMedia(dvd2);
                cart.addMedia(dvd1);

                // Sort cart items by title then cost and print
                cart.sortByTitleCost();
                cart.printCart();

                // Sort cart items by cost then title and print
                cart.sortByCostTitle();
                cart.printCart();
        }
}
