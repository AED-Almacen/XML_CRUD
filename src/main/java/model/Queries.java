package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Queries {
    private final String dbName;
    private StackOfBooks stackOfBooks;
    private final List<Book> books;

    public Queries() {
        this.dbName = "books.xml";
        this.stackOfBooks = new StackOfBooks();
        this.books = this.stackOfBooks.getBooks();
    }

    private void writeInDB() {
        JAXBContext context;
        Marshaller marshaller;

        try {
            context = JAXBContext.newInstance(StackOfBooks.class);
            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(this.stackOfBooks, new File(this.dbName));

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBook(String name, int pages) {
        this.books.add(new Book(this.books.size() + 1, name, pages));
        this.stackOfBooks.setNumberOfBooks(this.books.size());

        writeInDB();
    }

    public List<Book> readBooks() {
        JAXBContext context;
        Unmarshaller unmarshaller;

        try {
            context = JAXBContext.newInstance(StackOfBooks.class);
            unmarshaller = context.createUnmarshaller();

            this.stackOfBooks = (StackOfBooks) unmarshaller.unmarshal(new FileReader(this.dbName));

            return this.stackOfBooks.getBooks();
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Book readBook(int id) {
        return this.books.get(id);
    }

    public void updateBook(int id, String name, int pages) {
        this.books.get(id).setName(name);
        this.books.get(id).setPages(pages);

        writeInDB();
    }

    public void deleteBook(int id) {
        this.books.remove(id);
        this.stackOfBooks.setNumberOfBooks(this.books.size());

        writeInDB();
    }
}
