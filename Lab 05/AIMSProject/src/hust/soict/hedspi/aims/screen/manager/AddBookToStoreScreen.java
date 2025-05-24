package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createCenter() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField();

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener((ActionEvent e) -> {
            String title = titleField.getText();
            String category = categoryField.getText();
            float cost = Float.parseFloat(costField.getText());

            Book book = new Book(title, category, cost);
            store.addMedia(book);

            dispose();
            new StoreManagerScreen(store);
        });

        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.add(titleLabel);    panel.add(titleField);
        panel.add(categoryLabel); panel.add(categoryField);
        panel.add(costLabel);     panel.add(costField);
        panel.add(new JLabel());  panel.add(addButton);

        return panel;
    }
}