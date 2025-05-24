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
 private RadioButton penRadioButton;
 @FXML
 private RadioButton eraserRadioButton;
 private Paint currentColor = Color.BLACK;

 @FXML
 public void initialize() {

     penRadioButton.setSelected(true);
     currentColor = Color.BLACK;

 }

 @FXML
 void drawingAreaMouseDragged(MouseEvent event) {
     Circle newCircle = new Circle(event.getX(), event.getY(), 4, currentColor);
     drawingAreaPane.getChildren().add(newCircle);
 }

 @FXML
 void clearButtonPressed(ActionEvent event) {
     drawingAreaPane.getChildren().clear();
 }

 @FXML
 void toolSelected(ActionEvent event) {
     if (penRadioButton.isSelected()) {
         currentColor = Color.BLACK;
     } else if (eraserRadioButton.isSelected()) {
         currentColor = Color.WHITE;
     }
 }
}