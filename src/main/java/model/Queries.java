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

    public Queries() {
        this.dbName = "books.xml";
        this.stackOfBooks = new StackOfBooks();
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
        this.stackOfBooks.getBooks().add(new Book(this.stackOfBooks.getBooks().size() + 1, name, pages));
        this.stackOfBooks.setNumberOfBooks(this.stackOfBooks.getBooks().size());

        this.writeInDB();
    }

    public List<Book> readBooks() {
        JAXBContext context;
        Unmarshaller unmarshaller;

        try {
            context = JAXBContext.newInstance(StackOfBooks.class);
            unmarshaller = context.createUnmarshaller();

            this.stackOfBooks = (StackOfBooks) unmarshaller.unmarshal(new FileReader(this.dbName));
        } catch (JAXBException | FileNotFoundException e) {
            this.writeInDB();
        }

        return this.stackOfBooks.getBooks();
    }

    public Book readBook(int id) { return this.stackOfBooks.getBooks().get(id); }

    public void updateBook(int id, String name, int pages) {
        this.stackOfBooks.getBooks().get(id).setName(name);
        this.stackOfBooks.getBooks().get(id).setPages(pages);

        this.writeInDB();
    }

    public void deleteBook(int id) {
        this.stackOfBooks.getBooks().remove(id);
        this.stackOfBooks.setNumberOfBooks(this.stackOfBooks.getBooks().size());

        this.writeInDB();
    }
}
