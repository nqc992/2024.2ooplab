package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.PlayerException;

import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemController {
    private Cart cart;
    private Media media;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    public ItemController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        if (this.media != null && this.cart != null) {
            // TODO: Catch LimitReachedException if thrown by addMedia()
            cart.addMedia(this.media);
            System.out.println("Added " + this.media.getTitle() + " to cart from ItemController.");

            // Show confirmation alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("'" + this.media.getTitle() + "' has been added to the cart!");
            alert.showAndWait();

        } else {
            System.err.println("Cart or Media is null in ItemController.");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cannot add product to the cart.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (this.media instanceof Playable) {
            try {
                ((Playable) this.media).play(); // Call play(), may throw PlayerException
                // Show alert on successful play
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Now playing: " + this.media.getTitle());
                alert.showAndWait();
            } catch (PlayerException e) {
                // Catch PlayerException and show error details
                System.err.println("PlayerException caught in ItemController: " + e.getMessage());
                System.err.println("Exception toString(): " + e.toString());
                e.printStackTrace();

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

    public void setData(Media media) {
        this.media = media;
        if (media != null) {
            lblTitle.setText(media.getTitle());
            lblCost.setText(String.format("%.2f $", media.getCost()));
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
                btnPlay.setManaged(true);
            } else {
                btnPlay.setVisible(false);
                btnPlay.setManaged(false);
                HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60)); // Adjust layout if play button hidden
            }
        }
    }

    @FXML
    public void initialize() {
        // Moved btnPlay visibility logic into setData()
    }
}
