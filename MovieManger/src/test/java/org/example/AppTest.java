package org.example;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void createMovie(){

        Faker faker = new Faker();
        String quote = faker.backToTheFuture().quote();
        String beer = faker.beer().malt();

        String movieNameFake = faker.name().name();

        Movie movie = new Movie (movieNameFake, 2005);
        assertEquals(movieNameFake, movie.getTitle());
        assertNotNull(movie.getReviews());
        assertTrue(movie.getReviews().isEmpty());
    }
}
