package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        setTitle("Add Item");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
    }

    protected JPanel createNorth() {
        // reuse createMenuBar and createHeader
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(new StoreManagerScreen(store).createMenuBar());
        north.add(new StoreManagerScreen(store).createHeader());
        return north;
    }

    protected abstract JPanel createCenter();  // override in subclasses
}