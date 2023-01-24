package org.example;

import com.github.javafaker.Faker;
import org.example.Model.Director;
import org.example.Model.Movie;
import org.example.Model.Person;
import org.example.Model.Review;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testCreateMovie(){

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
    public void testCreateReview(){

        ArrayList reviews = new ArrayList<Review>();
        Director director = new Director();
        director.setName("Ridley Scott");
        director.setOscarQty(4);
        Review myReview = new Review(5, "Best film ever");
        reviews.add(myReview);

        // explain this sentence what? for what? how?
        Movie movie = new Movie("Alien", director, 1979, reviews );

        // find a comment within movie to test a review
        // from movie object get Reviews list position 0 and get comment from Review
        String findMyComment = movie.getReviews().get(0).getComment();

        assertEquals(findMyComment,"Best film ever" );
        assertNotEquals(findMyComment,"Worst film ever" );
    }

    @Test
    public void testOneToMany(){
        Person rscott = new Director();
        Movie alien = new Movie();
        Movie gladiator = new Movie();

        // be careful with this error
        // difference between compilation time and execution time
        // solution: casting?
        Director rscott_director = (Director) rscott;
        rscott_director.getMovies();
        rscott_director.getMovies().add(alien);

        Director sspilberg =  new Director();
        sspilberg.setOscarQty(2);
        Movie irobot = new Movie();
        Movie et = new Movie();
        Movie ai = new Movie();
        Movie munich = new Movie();
        Movie tiburon = new Movie();

        sspilberg.getMovies().add(tiburon);
        sspilberg.getMovies().add(irobot);
        sspilberg.getMovies().add(et);
        sspilberg.getMovies().add(ai);
        sspilberg.getMovies().add(munich);

        int qtyMoviesSpilberg = sspilberg.getMovies().size();
        assertNotEquals( 4,qtyMoviesSpilberg );
    }
}
