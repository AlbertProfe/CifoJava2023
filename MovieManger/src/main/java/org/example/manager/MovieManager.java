package org.example.manager;

import org.example.model.Movie;
import org.example.utils.InterfaceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        String titleToDelete =  InterfaceUtils.askString(reader,"Which one to delete?");
        movies.remove(titleToDelete);
        System.out.println("Movie deleted ...");
    }

    // Static method
    public static void updateMovie(Scanner reader) {
        // ask which movie to update
        // now you got Title
        // find movie in hashmap
        // if it exists > app will ask  user which field wants to update
        // create movie object
        // once we got new object movie, movies.replace
    }

    // Static method
    public static void getAllMoviesFromStorage() {
        //List<Movie> movies = new ArrayList<>();
        // to-do algorithmic to solve this method
        System.out.println(movies);
        //return movies;
    }

    // Static method
    public static void findMovie(Scanner reader) {
        String titleToFind =  InterfaceUtils.askString(reader,"Which one to find?");
        Movie movieFound = movies.get(titleToFind);
        System.out.println("Movie: " + movieFound);
    }

}
