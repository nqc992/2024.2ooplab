package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.PlayerException; // Import PlayerException

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Label lblTotalCost;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private Button btnViewStore;
    @FXML private Button btnPlaceOrder;

    private Store store;
    private Cart cart;
    private FilteredList<Media> filteredList;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        SortedList<Media> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblMedia.comparatorProperty());
        tblMedia.setItems(sortedList);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        btnPlay.setManaged(false);
        btnRemove.setManaged(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        updateButtonBar(newValue);
                    }
                }
        );
        updateTotalCost();

        ToggleGroup filterToggleGroup = new ToggleGroup();
        radioBtnFilterId.setToggleGroup(filterToggleGroup);
        radioBtnFilterTitle.setToggleGroup(filterToggleGroup);
        radioBtnFilterTitle.setSelected(true);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia();
        });

        radioBtnFilterId.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showFilteredMedia();
            }
        });

        radioBtnFilterTitle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showFilteredMedia();
            }
        });
    }

    void updateButtonBar(Media media) {
        if (media != null) {
            btnRemove.setVisible(true);
            btnRemove.setManaged(true);

            if (media instanceof Playable) {
                btnPlay.setVisible(true);
                btnPlay.setManaged(true);
            } else {
                btnPlay.setVisible(false);
                btnPlay.setManaged(false);
            }
        } else {
            btnRemove.setVisible(false);
            btnRemove.setManaged(false);
            btnPlay.setVisible(false);
            btnPlay.setManaged(false);
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable) {
            try {
                ((Playable) selectedMedia).play(); // Call play(), may throw PlayerException

                // If play is successful, show information alert
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Now playing: " + selectedMedia.getTitle());
                alert.showAndWait();
            } catch (PlayerException e) {
                // Catch PlayerException and show error details
                System.err.println("PlayerException caught in CartController: " + e.getMessage());
                System.err.println("Exception toString(): " + e.toString());
                e.printStackTrace(); // Print stack trace to console

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Media Playback Error");
                alert.setHeaderText("Unable to play media!");
                alert.setContentText(e.getMessage() + "\n\nError details: " + e.toString());
                alert.showAndWait();
            }
        } else {
            System.out.println("Selected item is not playable.");
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cannot Play");
            alert.setHeaderText(null);
            alert.setContentText("The selected item cannot be played.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            System.out.println("Removed: " + selectedMedia.getTitle() + " from cart.");
            updateTotalCost();
        } else {
            System.out.println("No item selected to remove.");
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to remove.");
            alert.showAndWait();
        }
    }

    private void updateTotalCost() {
        lblTotalCost.setText(String.format("%.2f $", cart.totalCost()));
    }

    void showFilteredMedia() {
        String filterText = tfFilter.getText().toLowerCase();

        filteredList.setPredicate(media -> {
            if (filterText == null || filterText.isEmpty()) {
                return true;
            }

            if (radioBtnFilterId.isSelected()) {
                try {
                    int filterId = Integer.parseInt(filterText);
                    return media.getId() == filterId;
                } catch (NumberFormatException e) {
                    return false;
                }
            } else {
                return media.getTitle().toLowerCase().contains(filterText);
            }
        });
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Store.fxml"));
            loader.setControllerFactory(c -> new hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController(store, cart));
            Parent root = loader.load();

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("AIMS - Store Screen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Store.fxml: " + e.getMessage());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Screen Transition Error");
            alert.setHeaderText("Failed to load the store screen.");
            alert.setContentText("Error details: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("There are no items in the cart to place an order.");
            alert.showAndWait();
            return;
        }

        // Place order: clear all items in the cart
        cart.clear();
        updateTotalCost(); // Update total cost to 0

        // Show order success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();
    }
}
