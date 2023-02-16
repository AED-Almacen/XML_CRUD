package controller;

import view.StacKOfBooks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ctrl implements ActionListener {
    private final StacKOfBooks stacKOfBooks;
    private void windowConfig() {
        this.stacKOfBooks.setTitle("Stack of Books");
        this.stacKOfBooks.setLocationRelativeTo(null);
        this.stacKOfBooks.setSize(400, 400);
        this.stacKOfBooks.setVisible(true);
    }

    public Ctrl() {
        this.stacKOfBooks = new StacKOfBooks();
        windowConfig();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
