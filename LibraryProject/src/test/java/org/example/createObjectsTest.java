package org.example;

import com.github.javafaker.Faker;
import org.example.manager.BookManager;
import org.example.manager.BorrowManager;
import org.example.manager.UserManager;
import org.example.model.*;
import org.example.utils.InterfaceUtils;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Date;
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
        //sspilberg.setOscarQty(2);
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
        //
        UserManager.createUsers(10);
    }

    @Test
    public void createFakerBooksTest(){
        //
        BookManager.createBooks(10);
    }

    @Test
    public void createBorrowTest(){
        //
        UserManager.createUsers(10);
        BookManager.createBooks(10);
        //
        Borrow newBorrow = new Borrow();
        // https://www.educative.io/answers/what-is-optionalisempty-in-java
        Optional<String> firstUserKey = UserManager.users.keySet().stream().findFirst();
        String firstUserId = "";
        if (firstUserKey.isPresent()) {
            firstUserId = firstUserKey.get();
        }
        User user = UserManager.users.get(firstUserId);
        String userName = user.getName();
        newBorrow.setUser(user);

        //
        Optional<String> firstBookKey = BookManager.books.keySet().stream().findFirst();
        String firstBookId ="";
        if (firstBookKey.isPresent()) {
            firstBookId = firstBookKey.get();
        }
        Book book = BookManager.books.get(firstBookId);
        String bookTitle = book.getTitle();
        newBorrow.setBook(book);
        //
        newBorrow.setDueDate(new Date());
        newBorrow.setReturnDate(new Date());
        newBorrow.setInitialBorrow(new Date());
        // enum?
        // https://www.educative.io/answers/what-are-enums-in-java
        // we must define status
        newBorrow.setBorrowStatus("open");
        //
        String borrowId = InterfaceUtils.createUUID();
        newBorrow.setBorrowId(borrowId);

        BorrowManager.borrows.put(borrowId,newBorrow );
        //System.out.println(newBorrow);

        assertEquals(borrowId, BorrowManager.borrows.get(borrowId).getBorrowId());
        assertEquals("open", BorrowManager.borrows.get(borrowId).getBorrowStatus());
        assertEquals(firstUserId, UserManager.users.get(firstUserId).getUserId());
        assertEquals(firstBookId, BookManager.books.get(firstBookId).getISBN());
        assertEquals(userName, UserManager.users.get(firstUserId).getName());
        assertEquals(bookTitle, BookManager.books.get(firstBookId).getTitle());
    }
}
