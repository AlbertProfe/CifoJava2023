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
    public static boolean createAndAddToStorage(Scanner reader) {
        boolean result = false;
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
        return result;
    }

    // Static method
    //public static void createAndAddToDynamoDB(Scanner reader) {}

    // Static method
    //public static void createAndAddToMongoDB(Scanner reader) {}

    // Static method
    public static boolean deleteMovie(String idMovie) {
        boolean result = false;
        // to-do algorithmic to solve this method
        // if idMovie exists in storage/hashMap
        // hashmap.remove(movie)
        // return result
        return result;
    }

    // Static method
    public static boolean updateMovie(Movie movie) {
        boolean result = false;
        // to-do algorithmic to solve this method
        return result;
    }

    // Static method
    public static void getAllMoviesFromStorage() {
        //List<Movie> movies = new ArrayList<>();
        // to-do algorithmic to solve this method
        System.out.println(movies);
        //return movies;
    }

    // Static method
    public static List<Movie> findMovie(String title) {
        List<Movie> movies = new ArrayList<>();
        // to-do algorithmic to solve this method
        return movies;
    }

}
