package hust.soict.hedspi.javafx;

import javafx.scene.control.Button; // Dòng đúng cho JavaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint; // Import này để lưu màu

public class PainterController {

 @FXML
 private Pane drawingAreaPane;

 @FXML
 private Button clearButton;

 @FXML
 private RadioButton penRadioButton; // Thêm
 @FXML
 private RadioButton eraserRadioButton; // Thêm

 // Thêm một biến để giữ màu vẽ hiện tại
 private Paint currentColor = Color.BLACK; // Mặc định là đen

 // Phương thức khởi tạo (được gọi sau khi FXML components được inject)
 @FXML
 public void initialize() {
     // Đảm bảo rằng penRadioButton được chọn mặc định và thiết lập màu ban đầu
     penRadioButton.setSelected(true);
     currentColor = Color.BLACK;

     // Bạn có thể thêm listener cho ToggleGroup để thay đổi màu khi công cụ được chọn
     // Tuy nhiên, việc sử dụng onAction trên mỗi RadioButton đơn giản hơn cho bài này
 }

 @FXML
 void drawingAreaMouseDragged(MouseEvent event) {
     Circle newCircle = new Circle(event.getX(), event.getY(), 4, currentColor); // Sử dụng currentColor
     drawingAreaPane.getChildren().add(newCircle);
 }

 @FXML
 void clearButtonPressed(ActionEvent event) {
     drawingAreaPane.getChildren().clear();
 }

 // Phương thức xử lý khi RadioButton được chọn
 @FXML
 void toolSelected(ActionEvent event) { // Phương thức này sẽ được gọi khi Pen hoặc Eraser được chọn
     if (penRadioButton.isSelected()) {
         currentColor = Color.BLACK;
     } else if (eraserRadioButton.isSelected()) {
         currentColor = Color.WHITE; // Màu nền của canvas
     }
 }
}