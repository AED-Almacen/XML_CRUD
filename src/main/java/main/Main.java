package main;

import controller.Ctrl;
import model.Queries;

public class Main {
    public static void main(String[] args) {
        new Ctrl();
        Queries queries = new Queries();

        queries.createBook("Odisea 2002", 400);
        queries.createBook("Odisea 2001", 500);

        System.out.println(queries.readBook(0).getName());
    }
}