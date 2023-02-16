package view;

import javax.swing.*;

public class StacKOfBooks extends JFrame {
    private JPanel window;
    private JScrollPane ScrollPane;
    private JTable table;
    private JPanel form;


    public StacKOfBooks() {
        super();
        setContentPane(window);
    }

    public JPanel getWindow() {
        return this.window;
    }

    public JTable getTable() {
        return this.table;
    }

    public JScrollPane getScrollPane() {
        return ScrollPane;
    }
}
