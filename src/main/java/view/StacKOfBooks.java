package view;

import javax.swing.*;

public class StacKOfBooks extends JFrame {
    private JPanel window;
    private JTable books;

    public StacKOfBooks() {
        super();
        setContentPane(window);
    }

    public JPanel getWindow() {
        return this.window;
    }

    public JTable getTable() {
        return this.books;
    }
}
