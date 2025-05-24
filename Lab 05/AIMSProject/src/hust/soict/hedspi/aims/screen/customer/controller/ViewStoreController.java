package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.cart.Cart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewStoreController {

    private Store store;
    private Cart cart;

    // Modified constructor: receives both Store and Cart
    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

    // Event handler method for the "View Cart" button
    @FXML
    void btnViewCartPressed(ActionEvent event) { // Named "Pressed" for consistency
        try {
            // Load the FXML of the Cart Screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Cart.fxml"));

            // Set controller factory to pass Store and Cart into CartController
            loader.setControllerFactory(c -> new CartController(store, cart));

            Parent root = loader.load();

            // Get the current Stage from the clicked button
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();

            // Create new Scene and set it on the Stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cart Screen"); // Set title for the Cart screen
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Cart.fxml: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH = "screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;

        if (store == null || store.getItemsInStore() == null) {
            System.err.println("Store or items in store are not initialized. Cannot display items.");
            return;
        }

        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH));

                // ItemController needs Cart to handle adding to cart
                ItemController itemController = new ItemController(cart);
                fxmlLoader.setController(itemController);

                Parent itemPane = fxmlLoader.load();

                // Cast to Media since ItemController.setData expects a Media object
                itemController.setData((hust.soict.hedspi.aims.media.Media) store.getItemsInStore().get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridPane.add(itemPane, column++, row);
                GridPane.setMargin(itemPane, new Insets(20, 10, 10, 10));

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading item FXML or setting data: " + e.getMessage());
            } catch (ClassCastException e) {
                System.err.println("ClassCastException: Expected Media object. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
