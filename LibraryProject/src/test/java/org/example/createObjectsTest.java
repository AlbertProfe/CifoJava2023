package org.example;

import com.github.javafaker.Faker;
import org.example.manager.UserManager;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Person;
import org.example.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class createObjectsTest {

    @Test
    public void testCreateBook(){

        Faker faker = new Faker();
        String bookTitle = faker.book().title();

        Book newbook = new Book();

        Book book = new Book(bookTitle, 2005);
        assertEquals(bookTitle, book.getTitle());

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

    @Test
    public void createFakerUsersTest(){

        UserManager.createUsers(1000);

    }
}
