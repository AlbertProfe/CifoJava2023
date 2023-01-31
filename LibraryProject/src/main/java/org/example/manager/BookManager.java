package org.example.manager;

import org.example.model.Book;
import org.example.utils.InterfaceUtils;
import java.util.HashMap;
import java.util.Scanner;

public class BookManager {

    public static HashMap<String, Book> movies = new HashMap<String, Book>();

    // Static method
    public static void createAndAddToStorage(Scanner reader) {
        // ask user for Movie parameters
        String movieName = InterfaceUtils.askString(reader, "Movie name?");
        int movieYear = InterfaceUtils.askInt(reader, "Movie year?");
        // create Movie object
        Book myBook = new Book();
        myBook.setTitle(movieName);
        myBook.setYear(movieYear);
        //add to hashmap
        movies.put(movieName, myBook);
        // return result to view
        System.out.println("Movie added ...");
    }

    // Static method
    public static void deleteMovie(Scanner reader) {
        // ask user for Movie to delete
        String titleToDelete =  InterfaceUtils.askString(reader,"Which one to delete?");
        // remove object from movies
        movies.remove(titleToDelete);
        System.out.println("Movie deleted ...");
    }

    // Static method
    public static void updateMovie(Scanner reader) {
        // copy/paste from findMovie
        String titleToUpdate =  InterfaceUtils.askString(reader,"Which one to update?");
        Book bookFound = movies.get(titleToUpdate);
        System.out.println("Movie: " + bookFound);
        // copy/paste from createAndAddToStorage
        int movieYear = InterfaceUtils.askInt(reader, "Movie year?");
        // let s update
        Book bookToUpdate = new Book();
        bookToUpdate.setTitle(bookFound.getTitle());
        bookToUpdate.setYear(movieYear);
        // let s replace on movies hashmap
        movies.replace(bookFound.getTitle(), bookToUpdate);
        System.out.println("Movie updated ...");
    }

    // Static method
    public static void getAllMoviesFromStorage() {
        // print all movies
        System.out.println(movies);
    }

    // Static method
    public static void findMovie(Scanner reader) {
        // ask user for movie
        String titleToFind =  InterfaceUtils.askString(reader,"Which one to find?");
        // get movie from movies by key
        Book bookFound = movies.get(titleToFind);
        // print movie
        System.out.println("Movie: " + bookFound);
    }

}
