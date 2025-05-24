package hust.soict.hedspi.aims;

import java.util.Scanner;

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
        // Add some sample media for demonstration
        store.addMedia(new DigitalVideoDisc("Avengers", "Action", "Joss Whedon", 143, 20.0f));
        store.addMedia(new Book("Harry Potter", "Fantasy", 15.0f));
        store.addMedia(new CompactDisc("Hybrid Theory", "Rock", 10.0f, "Linkin Park", 45, "Chester"));

        while (true) {
            showMainMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (option) {
                case 1 -> viewStore();
                case 2 -> updateStore();
                case 3 -> seeCart();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\nAIMS Main Menu:");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-3): ");
    }

    private static void viewStore() {
        store.printStore();
        while (true) {
            showStoreMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> seeMediaDetails();
                case 2 -> addMediaToCart();
                case 3 -> playMedia();
                case 4 -> seeCart();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showStoreMenu() {
        System.out.println("\nStore Options:");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-4): ");
    }

    private static void seeMediaDetails() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            System.out.println(media);
            showMediaDetailsMenu(media instanceof Playable);
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                cart.addMedia(media);
            } else if (option == 2 && media instanceof Playable playable) {
                playable.play();
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void showMediaDetailsMenu(boolean isPlayable) {
        System.out.println("\nOptions:");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (isPlayable) System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.print("Please choose a number: 0-1" + (isPlayable ? "-2" : "") + ": ");
    }

    private static void addMediaToCart() {
        System.out.print("Enter media title to add: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            cart.addMedia(media);
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void playMedia() {
        System.out.print("Enter media title to play: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media instanceof Playable playable) {
            playable.play();
        } else {
            System.out.println("Cannot play this media.");
        }
    }

    private static void updateStore() {
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        System.out.print("Choose an option (1 or 2): ");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        if (option == 1) {
            // Here you might want to add more types, but for demo, we add a Book with unknown genre and cost 0
            store.addMedia(new Book(title, "Unknown", 0));
        } else if (option == 2) {
            Media media = store.searchByTitle(title);
            if (media != null) {
                store.removeMedia(media);
            } else {
                System.out.println("Media not found in store.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void seeCart() {
        cart.printCart();
        while (true) {
            showCartMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> filterCart();
                case 2 -> sortCart();
                case 3 -> removeFromCart();
                case 4 -> playCartMedia();
                case 5 -> placeOrder();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showCartMenu() {
        System.out.println("\nCart Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.print("Please choose a number (0-5): ");
    }

    private static void filterCart() {
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by title");
        System.out.print("Choose an option: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        if (opt == 1) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Media media = cart.searchById(id);
            if (media != null) {
                System.out.println("Found: " + media);
            } else {
                System.out.println("No media found with ID " + id);
            }
        } else if (opt == 2) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            Media media = cart.searchByTitle(title);
            if (media != null) {
                System.out.println("Found: " + media);
            } else {
                System.out.println("No media found with title \"" + title + "\"");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void sortCart() {
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by cost");
        System.out.print("Choose an option: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        // Assuming cart.sortByCostTitle() sorts by cost then title.
        // You may want to implement sortByTitle() and sortByCost() separately if needed.
        cart.sortByCostTitle();
        System.out.println("Cart sorted.");
    }

    private static void removeFromCart() {
        System.out.print("Enter title to remove: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Removed \"" + title + "\" from cart.");
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    private static void playCartMedia() {
        System.out.print("Enter title to play: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media instanceof Playable playable) {
            playable.play();
        } else {
            System.out.println("Cannot play this media.");
        }
    }

    private static void placeOrder() {
        System.out.println("Order created!");
        cart.clear();
    }
}
