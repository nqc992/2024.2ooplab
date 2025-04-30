package hust.soict.hedspi.swing;

import java.awt.*;
import java.awt.event.*;

// Create class AWTAccumulator that extends Frame
public class AWTAccumulator extends Frame {
    private TextField tfInput;    // Input TextField
    private TextField tfOutput;   // Output TextField (read-only)
    private int sum = 0;          // Accumulated sum, init to 0

    // Constructor to setup the GUI components and event handlers
    public AWTAccumulator() {
        setLayout(new GridLayout(2, 2)); // Set layout to 2 rows, 2 columns

        // Add Label and TextField for input
        add(new Label("Enter an Integer: "));
        tfInput = new TextField(10); // 10 columns wide
        add(tfInput);
        // Add an ActionListener to the input TextField
        tfInput.addActionListener(new TFInputListener());

        // Add Label and TextField for output
        add(new Label("The Accumulated Sum is: "));
        tfOutput = new TextField(10); // 10 columns wide
        tfOutput.setEditable(false);  // Make output TextField read-only
        add(tfOutput);

        setTitle("AWT Accumulator"); // Set the window title
        setSize(350, 120);           // Set the window size
        setVisible(true);            // Make the window visible

        // Handle window closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Terminate the program
            }
        });
    }

    // The entry main() method
    public static void main(String[] args) {
        // Invoke the constructor to setup the GUI, by allocating an instance
        new AWTAccumulator();
    }

    // Private inner class to handle the ActionListener for the input TextField
    private class TFInputListener implements ActionListener {
        // ActionEvent handler
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Get the integer entered into the input TextField
            int numberIn = Integer.parseInt(tfInput.getText());
            // Accumulate the number into the sum
            sum += numberIn;
            // Clear the input TextField
            tfInput.setText("");
            // Display the accumulated sum in the output TextField
            tfOutput.setText(sum + ""); // Convert sum to String
        }
    }
}