package org.example;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void createMovie(){

        Faker faker = new Faker();
        String quote = faker.backToTheFuture().quote();
        String beer = faker.beer().malt();

        String movieName = faker.name().name();

        Movie movie = new Movie (movieName, 2005, new ArrayList<>());
        assertEquals(movieName, movie.getTitle());
        //assertNotNull(movie.getReviews());
        //assertTrue(movie.getReviews().isEmpty());

    }


}
