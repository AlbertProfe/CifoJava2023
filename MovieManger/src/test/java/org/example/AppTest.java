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


        Movie carlamovie = new Movie();

        Movie movie = new Movie (movieNameFake, 2005);
        assertEquals(movieNameFake, movie.getTitle());
        assertNotNull(movie.getReviews());
        assertTrue(movie.getReviews().isEmpty());
    }

    @Test
    public void createReview(){

        ArrayList reviews = new ArrayList<Review>();
        Director director = new Director("Ridley Scott");
        Review myreview = new Review(5, "Best film ever");
        reviews.add(myreview);

        // explain this sentence what? for what? how?
        Movie movie = new Movie("Alien", director, 1979, reviews );

        String findMyComment = movie.getReviews().get(0).getComment();

        assertEquals(findMyComment,"Best film ever" );
        assertNotEquals(findMyComment,"Worst film ever" );


    }
}
