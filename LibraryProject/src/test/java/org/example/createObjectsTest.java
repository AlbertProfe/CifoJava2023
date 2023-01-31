package org.example;

import com.github.javafaker.Faker;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class createObjectsTest {

    @Test
    public void testCreateMovie(){

        Faker faker = new Faker();
        String quote = faker.backToTheFuture().quote();
        String beer = faker.beer().malt();
        String movieNameFake = faker.name().name();

        Book carlamovie = new Book();

        Book book = new Book(movieNameFake, 2005);
        assertEquals(movieNameFake, book.getTitle());

    }




    @Test
    public void testOneToMany(){
        Person rscott = new Author();
        Book alien = new Book();
        Book gladiator = new Book();

        // be careful with this error
        // difference between compilation time and execution time
        // solution: casting?
        Author rscott_author = (Author) rscott;
        rscott_author.getBooks();
        rscott_author.getBooks().add(alien);

        Author sspilberg =  new Author();
        sspilberg.setOscarQty(2);
        Book irobot = new Book();
        Book et = new Book();
        Book ai = new Book();
        Book munich = new Book();
        Book tiburon = new Book();

        sspilberg.getBooks().add(tiburon);
        sspilberg.getBooks().add(irobot);
        sspilberg.getBooks().add(et);
        sspilberg.getBooks().add(ai);
        sspilberg.getBooks().add(munich);

        int qtyMoviesSpilberg = sspilberg.getBooks().size();
        assertNotEquals( 4,qtyMoviesSpilberg );
    }
}
