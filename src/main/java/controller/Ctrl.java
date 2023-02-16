package controller;

import model.Book;
import model.Queries;
import view.StacKOfBooks;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ctrl implements ActionListener {
    private final StacKOfBooks stacKOfBooks;
    private final Queries queries;

    private void windowConfig() {
        this.stacKOfBooks.setTitle("Stack of Books");
        this.stacKOfBooks.setLocationRelativeTo(null);
        this.stacKOfBooks.setSize(700, 450);
        this.stacKOfBooks.setVisible(true);
    }

    private void createTable(List<Book> books) {
        Object[][] data = new Object[books.size()][];

        for (int i = 0; i < books.size(); i++) {
            data[i] = new String[]{
                    String.valueOf(books.get(i).getId()),
                    books.get(i).getName(),
                    String.valueOf(books.get(i).getPages())
            };
        }

        this.stacKOfBooks.getTable().setModel(new DefaultTableModel(
                data,
                new String[]{"Id", "Name", "Pages"}
        ));
    }

    public Ctrl() {
        this.stacKOfBooks = new StacKOfBooks();
        this.queries = new Queries();
        windowConfig();

        for (int i = 0; i < 40; i++) {
            this.queries.createBook("test", 200);
        }

        this.createTable(queries.readBooks());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
