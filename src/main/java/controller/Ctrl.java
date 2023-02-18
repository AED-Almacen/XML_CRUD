package controller;

import model.Book;
import model.Queries;
import view.StacKOfBooks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

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

    private void cleanText() {
        this.stacKOfBooks.getIdText().setText("");
        this.stacKOfBooks.getTitleText().setText("");
        this.stacKOfBooks.getPagesText().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.stacKOfBooks.getAddBtn()) {
            try {
                String title = this.stacKOfBooks.getTitleText().getText();
                String pages = this.stacKOfBooks.getPagesText().getText();

                if (Objects.equals(title, "") || Objects.equals(pages, "")) {
                    JOptionPane.showMessageDialog(null,
                            "Debe rellenar el título y el número de páginas");
                } else {
                    this.queries.createBook(title, Integer.parseInt(pages));
                    this.writeInTable(queries.readBooks());
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "El número de páginas debe de ser un número");
            }

            this.cleanText();

        } else if (e.getSource() == this.stacKOfBooks.getUpdateBtn()) {
            try {
                int id = Integer.parseInt(this.stacKOfBooks.getIdText().getText())-1;
                String title = this.stacKOfBooks.getTitleText().getText();
                String pages = this.stacKOfBooks.getPagesText().getText();

                if ((Objects.equals(title, "") || Objects.equals(pages, ""))) {
                    JOptionPane.showMessageDialog(null,
                            "Debe rellenar el título y el número de páginas");
                } else if (queries.readBook(id).getId() == id + 1) {
                    this.queries.updateBook(id, title, Integer.parseInt(pages));
                }

                this.writeInTable(queries.readBooks());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "El id debe de ser válido. Todos los campos deben ser rellenados.");
            }

            this.cleanText();

        } else if (e.getSource() == this.stacKOfBooks.getDropBtn()) {
            try {
                int id = Integer.parseInt(this.stacKOfBooks.getIdText().getText())-1;

                if (queries.readBook(id).getId() == id + 1) {
                    this.queries.deleteBook(id);
                }

                this.writeInTable(queries.readBooks());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "El id debe de ser válido.");
            }

            this.cleanText();
        }
    }
}
