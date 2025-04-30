package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createCenter() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10)); // 6 fields + button

        // Create labels and text fields
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField();

        JLabel directorLabel = new JLabel("Director:");
        JTextField directorField = new JTextField();

        JLabel lengthLabel = new JLabel("Length:");
        JTextField lengthField = new JTextField();

        JLabel artistLabel = new JLabel("Artist:");
        JTextField artistField = new JTextField();

        // Add button
        JButton addButton = new JButton("Add CD");
        addButton.addActionListener((ActionEvent e) -> {
            try {
                String title = titleField.getText();
                String category = categoryField.getText();
                float cost = Float.parseFloat(costField.getText());
                String director = directorField.getText();
                int length = Integer.parseInt(lengthField.getText());
                String artist = artistField.getText();

                CompactDisc cd = new CompactDisc(title, category, cost, director, length, artist);
                store.addMedia(cd);

                JOptionPane.showMessageDialog(null, "CD added successfully!");
                dispose();
                new StoreManagerScreen(store);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please check your data.");
            }
        });

        // Add components to panel
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.add(titleLabel);    panel.add(titleField);
        panel.add(categoryLabel); panel.add(categoryField);
        panel.add(costLabel);     panel.add(costField);
        panel.add(directorLabel); panel.add(directorField);
        panel.add(lengthLabel);   panel.add(lengthField);
        panel.add(artistLabel);   panel.add(artistField);
        panel.add(new JLabel());  panel.add(addButton);

        return panel;
    }
}
