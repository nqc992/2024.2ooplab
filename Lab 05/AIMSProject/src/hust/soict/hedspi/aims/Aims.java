package hust.soict.hedspi.aims;

import java.util.*;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample media added for testing
        store.addMedia(new DigitalVideoDisc("Avengers", "Action", "Joss Whedon", 143, 20.0f));
        store.addMedia(new Book("Harry Potter", "Fantasy", 15.0f));
        store.addMedia(new CompactDisc("Hybrid Theory", "Rock", 10.0f, "Linkin Park", 45, "Chester"));

        while (true) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> viewStore();
                case 2 -> updateStore();
                case 3 -> seeCart();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void viewStore() {
        store.printStore();
        while (true) {
            storeMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> seeMediaDetails();
                case 2 -> addMediaToCart();
                case 3 -> playMedia();
                case 4 -> seeCart();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void seeMediaDetails() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            System.out.println(media);
            if (media instanceof Playable) {
                mediaDetailsMenu(true);
            } else {
                mediaDetailsMenu(false);
            }
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) cart.addMedia(media);
            else if (option == 2 && media instanceof Playable playable) playable.play();
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void mediaDetailsMenu(boolean isPlayable) {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (isPlayable) System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-" + (isPlayable ? "2" : "1"));
    }

    public static void addMediaToCart() {
        System.out.print("Enter media title to add: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            cart.addMedia(media);
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMedia() {
        System.out.print("Enter media title to play: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media instanceof Playable playable) {
            playable.play();
        } else {
            System.out.println("Cannot play this media.");
        }
    }

    public static void updateStore() {
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        if (option == 1) {
            store.addMedia(new Book(title, "Unknown", 0));
        } else if (option == 2) {
            Media media = store.searchByTitle(title);
            if (media != null) store.removeMedia(media);
        }
    }

    public static void seeCart() {
        cart.printCart();
        while (true) {
            cartMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> filterCart();
                case 2 -> sortCart();
                case 3 -> removeFromCart();
                case 4 -> playCartMedia();
                case 5 -> placeOrder();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void filterCart() {
        System.out.println("1. Filter by id\n2. Filter by title");
        int opt = scanner.nextInt();
        scanner.nextLine();
        if (opt == 1) {
            System.out.print("Enter id: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            cart.searchById(id);
        } else {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            cart.searchByTitle(title);
        }
    }

    public static void sortCart() {
        System.out.println("1. Sort by title\n2. Sort by cost");
        int opt = scanner.nextInt();
        scanner.nextLine();
        if (opt == 1) {
            cart.sortByCostTitle();
        } else {
            cart.sortByCostTitle();
        }
    }

    public static void removeFromCart() {
        System.out.print("Enter title to remove: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null) cart.removeMedia(media);
    }

    public static void playCartMedia() {
        System.out.print("Enter title to play: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media instanceof Playable playable) {
            playable.play();
        } else {
            System.out.println("Cannot play this media.");
        }
    }

    public static void placeOrder() {
        System.out.println("Order created!");
        cart.clear();
    }
}
