package hust.soict.hedspi.swing;

import javax.swing.*;    // Needed for Swing components like JFrame, JTextField, JLabel
import java.awt.*;       // Needed for LayoutManager (GridLayout) and Container
import java.awt.event.*; // Needed for ActionListener and ActionEvent

/**
 * Create class SwingAccumulator that extends JFrame
 */
public class SwingAccumulator extends JFrame {
    private JTextField tfInput;    // Input JTextField
    private JTextField tfOutput;   // Output JTextField (read-only)
    private int sum = 0;          // Accumulated sum, init to 0

    // Constructor to setup the GUI components and event handlers
    public SwingAccumulator() {
        Container cp = getContentPane(); // Get the content pane of the JFrame
        cp.setLayout(new GridLayout(2, 2, 5, 5)); // Set layout to 2 rows, 2 columns with gaps

        // Add Label and TextField for input
        cp.add(new JLabel("Enter an Integer: "));
        tfInput = new JTextField(10); // 10 columns wide
        cp.add(tfInput);
        // Add an ActionListener to the input TextField
        tfInput.addActionListener(new TFInputListener());

        // Add Label and TextField for output
        cp.add(new JLabel("The Accumulated Sum is: "));
        tfOutput = new JTextField(10); // 10 columns wide
        tfOutput.setEditable(false);  // Make output TextField read-only
        cp.add(tfOutput);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
        setTitle("Swing Accumulator"); // Set the window title
        setSize(350, 120);           // Set the window size
        setVisible(true);            // Make the window visible
    }

    // The entry main() method
    public static void main(String[] args) {
        // Run the GUI construction in the Event Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingAccumulator(); // Let the constructor do the job
            }
        });
    }

    // Private inner class to handle the ActionListener for the input TextField
    private class TFInputListener implements ActionListener {
        // ActionEvent handler - Called back upon hitting Enter key on tfInput
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                // Get the integer entered into the input TextField
                int numberIn = Integer.parseInt(tfInput.getText());
                // Accumulate the number into the sum
                sum += numberIn;
                // Clear the input TextField
                tfInput.setText("");
                // Display the accumulated sum in the output TextField
                tfOutput.setText(sum + ""); // Convert sum to String
            } catch (NumberFormatException ex) {
                // Handle error if input is not a valid integer
                JOptionPane.showMessageDialog(SwingAccumulator.this,
                        "Invalid input. Please enter an integer.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                tfInput.requestFocus(); // Set focus back to input field
                tfInput.selectAll();    // Select the invalid text
            }
        }
    }
}