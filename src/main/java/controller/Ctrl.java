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

    private void writeInTable(List<Book> books) {
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
                new String[]{"Id", "Nombre", "Nº de páginas"}
        ));
    }

    public Ctrl() {
        this.stacKOfBooks = new StacKOfBooks();
        this.queries = new Queries();
        windowConfig();

        this.stacKOfBooks.getAddBtn().addActionListener(this);
        this.stacKOfBooks.getUpdateBtn().addActionListener(this);
        this.stacKOfBooks.getDropBtn().addActionListener(this);
        this.writeInTable(queries.readBooks());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.stacKOfBooks.getAddBtn()) {

            //JOptionPane.showMessageDialog(null,"addBtn");

            this.queries.createBook(this.stacKOfBooks.getTitleText().getText(),
                    Integer.parseInt(this.stacKOfBooks.getPagesText().getText()));

            this.writeInTable(queries.readBooks());

        } else if (e.getSource() == this.stacKOfBooks.getUpdateBtn()) {

            this.queries.updateBook(Integer.parseInt(this.stacKOfBooks.getIdText().getText())-1,
                    this.stacKOfBooks.getTitleText().getText(),
                    Integer.parseInt(this.stacKOfBooks.getPagesText().getText()));

            this.writeInTable(queries.readBooks());

        } else if (e.getSource() == this.stacKOfBooks.getDropBtn()) {

            this.queries.deleteBook(Integer.parseInt(this.stacKOfBooks.getIdText().getText())-1);
            this.writeInTable(queries.readBooks());
        }
    }
}
