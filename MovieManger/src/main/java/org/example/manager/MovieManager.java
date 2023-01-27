package org.example.manager;

import org.example.model.Movie;
import org.example.utils.InterfaceUtils;
import java.util.HashMap;
import java.util.Scanner;

public class MovieManager {

    public static HashMap<String, Movie> movies = new HashMap<String,Movie>();

    // Static method
    public static void createAndAddToStorage(Scanner reader) {
        // ask user for Movie parameters
        String movieName = InterfaceUtils.askString(reader, "Movie name?");
        int movieYear = InterfaceUtils.askInt(reader, "Movie year?");
        // create Movie object
        Movie myMovie = new Movie();
        myMovie.setTitle(movieName);
        myMovie.setYear(movieYear);
        //add to hashmap
        movies.put(movieName, myMovie);
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
        Movie movieFound = movies.get(titleToUpdate);
        System.out.println("Movie: " + movieFound);
        // copy/paste from createAndAddToStorage
        int movieYear = InterfaceUtils.askInt(reader, "Movie year?");
        // let s update
        Movie movieToUpdate = new Movie();
        movieToUpdate.setTitle(movieFound.getTitle());
        movieToUpdate.setYear(movieYear);
        // let s replace on movies hashmap
        movies.replace(movieFound.getTitle(), movieToUpdate);
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
        Movie movieFound = movies.get(titleToFind);
        // print movie
        System.out.println("Movie: " + movieFound);
    }

}
