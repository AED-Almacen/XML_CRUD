package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = { "numberOfBooks", "books"})
public class StackOfBooks {
    private final List<Book> books;
    private int numberOfBooks;

    public StackOfBooks() {
        this.books = new ArrayList<>();
        this.numberOfBooks = 0;
    }

    @XmlElement(name = "book")
    public List<Book> getBooks() {
        return this.books;
    }

    public int getNumberOfBooks() {
        return this.numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
