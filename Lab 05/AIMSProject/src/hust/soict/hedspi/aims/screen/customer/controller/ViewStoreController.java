package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.cart.Cart; // Import Cart

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent; // Cho loader.load()
import javafx.scene.Scene;  // Để tạo Scene mới
import javafx.scene.control.Button; // Để lấy Stage từ event source
import javafx.stage.Stage;  // Để thay đổi Stage

public class ViewStoreController {

	private Store store;
	private Cart cart; // Thêm thuộc tính cart

    // Constructor đã sửa đổi: nhận cả Store và Cart
	public ViewStoreController (Store store, Cart cart) {
		this.store = store;
		this.cart = cart;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

    // Phương thức xử lý sự kiện cho nút "View Cart"
    @FXML
    void btnViewCartPressed(ActionEvent event) { // Đặt tên là Pressed cho nhất quán
        try {
            // Tải FXML của Cart Screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Cart.fxml"));

            // Thiết lập Controller Factory để truyền Store và Cart vào CartController
            loader.setControllerFactory(c -> new CartController(store, cart));

            Parent root = loader.load(); // Tải FXML

            // Lấy Stage hiện tại từ nút đã click
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();

            // Tạo Scene mới và đặt cho Stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cart Screen"); // Đặt tiêu đề cho màn hình Cart
            stage.show(); // Hiển thị màn hình Cart

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

                // ItemController cần nhận Cart để xử lý thêm vào giỏ hàng
                ItemController itemController = new ItemController(cart);
                fxmlLoader.setController(itemController);

                Parent anchorPane = fxmlLoader.load();

                // Chuyển đổi sang kiểu Media nếu ItemController.setData chấp nhận Media
                itemController.setData((hust.soict.hedspi.aims.media.Media)store.getItemsInStore().get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
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