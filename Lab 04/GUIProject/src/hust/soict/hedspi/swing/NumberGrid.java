package hust.soict.hedspi.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGrid extends JFrame {
    private final JButton[] numberButtons = new JButton[10];
    private final JButton btnDelete = createButton("DEL");
    private final JButton btnClear = createButton("C");
    private final JTextField tfDisplay = new JTextField();

    public NumberGrid() {
        super("Number Grid");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 200);
        setLayout(new BorderLayout());

        setupDisplay();
        setupButtons();

        setVisible(true);
    }

    private void setupDisplay() {
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(tfDisplay, BorderLayout.NORTH);
    }

    private void setupButtons() {
        JPanel panel = new JPanel(new GridLayout(4, 3));
        ButtonListener listener = new ButtonListener();

        // Add number buttons 1-9
        for (int i = 1; i <= 9; i++) {
            numberButtons[i] = createButton(String.valueOf(i));
            numberButtons[i].addActionListener(listener);
            panel.add(numberButtons[i]);
        }

        // Add DEL, 0, C
        btnDelete.addActionListener(listener);
        numberButtons[0] = createButton("0");
        numberButtons[0].addActionListener(listener);
        btnClear.addActionListener(listener);

        panel.add(btnDelete);
        panel.add(numberButtons[0]);
        panel.add(btnClear);

        add(panel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        return new JButton(text);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "DEL":
                    String currentText = tfDisplay.getText();
                    if (!currentText.isEmpty()) {
                        tfDisplay.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;
                case "C":
                    tfDisplay.setText("");
                    break;
                default:
                    if (command.matches("\\d")) {  // Kiểm tra nếu là chữ số
                        tfDisplay.setText(tfDisplay.getText() + command);
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGrid::new);
    }
}
