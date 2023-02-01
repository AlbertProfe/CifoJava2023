package org.example.manager;

import org.example.model.Book;
import org.example.utils.InterfaceUtils;
import java.util.HashMap;
import java.util.Scanner;

public class BookManager {

    public static HashMap<String, Book> books = new HashMap<String, Book>();

    // Static method
    public static void createAndAddToStorage(Scanner reader) {
        // ask user for Movie parameters
        String bookTitle = InterfaceUtils.askString(reader, "Book name?");
        int bookYear = InterfaceUtils.askInt(reader, "Book year?");
        // create Movie object
        Book myBook = new Book();
        myBook.setTitle(bookTitle);
        myBook.setYear(bookYear);
        //add to hashmap
        books.put(bookTitle, myBook);
        // return result to view
        System.out.println("Movie added ...");
    }

    // Static method
    public static void deleteMovie(Scanner reader) {
        // ask user for Movie to delete
        String titleToDelete =  InterfaceUtils.askString(reader,"Which one to delete?");
        // remove object from movies
        books.remove(titleToDelete);
        System.out.println("Movie deleted ...");
    }

    // Static method
    public static void updateMovie(Scanner reader) {
        // copy/paste from findMovie
        String titleToUpdate =  InterfaceUtils.askString(reader,"Which one to update?");
        Book bookFound = books.get(titleToUpdate);
        System.out.println("Book: " + bookFound);
        // copy/paste from createAndAddToStorage
        int bookYear = InterfaceUtils.askInt(reader, "Book year?");
        // let s update
        Book bookToUpdate = new Book();
        bookToUpdate.setTitle(bookFound.getTitle());
        bookToUpdate.setYear(bookYear);
        // let s replace on movies hashmap
        books.replace(bookFound.getTitle(), bookToUpdate);
        System.out.println("Book updated ...");
    }

    // Static method
    public static void getAllMoviesFromStorage() {
        // print all movies
        System.out.println(books);
    }

    // Static method
    public static void findMovie(Scanner reader) {
        // ask user for movie
        String titleToFind =  InterfaceUtils.askString(reader,"Which one to find?");
        // get movie from movies by key
        Book bookFound = books.get(titleToFind);
        // print movie
        System.out.println("Book: " + bookFound);
    }

}
