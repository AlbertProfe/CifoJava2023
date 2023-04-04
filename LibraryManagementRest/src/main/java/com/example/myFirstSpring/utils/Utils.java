package com.example.myFirstSpring.utils;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.model.Borrow;
import com.example.myFirstSpring.model.User;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


//import static com.example.myFirstSpring.service.BookService.books;
import static com.example.myFirstSpring.service.UserService.users;

public class Utils {


    public static String createUUID() {

        UUID uuid = UUID.randomUUID();
        //System.out.println("UUID generated ( version - " + uuid.version() + ") : " +  uuid);
        String id = uuid.toString();

        return id;
    }

    public static String createISBN() {

        UUID uuid = UUID.randomUUID();
        //System.out.println("UUID generated ( version - " + uuid.version() + ") : " +  uuid);
        String isbn = uuid.toString().toUpperCase().replace("-", "");

        return isbn;
    }

    public static void populateFakeUsers(int number, HashMap<String, User> users) {
        // create faker object to use as
        // builder for user
        Faker faker = new Faker();
        User newUser;

        for (int i = 0; i < number; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1001, 9999 + 1);
            newUser = new User();

            String userId = Utils.createUUID();
            newUser.setUserId(userId);

            String userName = faker.name().firstName();
            newUser.setName(userName);

            String userAddress = faker.address().fullAddress();
            newUser.setAddress(userAddress);

            int userAge = faker.number().numberBetween(0, 100);
            newUser.setAge(userAge);

            users.put(userId, newUser);


        }
        //System.out.println(users.size());
    }

    public static void populateFakeBooks(int number, HashMap<String, Book> books) {

        Faker faker = new Faker();
        Book newBook;

        for (int i = 0; i < number; i++) {

            newBook = new Book();

            String bookId = Utils.createUUID();
            newBook.setBookId(bookId);

            String title = faker.book().title();
            newBook.setTitle(title);

            String isbn = createISBN();
            newBook.setIsbn(isbn);

            String author = faker.book().author();
            newBook.setAuthor(author);


            int pages = faker.number().numberBetween(250, 999);
            newBook.setPages(pages);

            int publishedYear = faker.number().numberBetween(1800, 2020);
            newBook.setPublishedYear(publishedYear);


            books.put(bookId, newBook);

        }
        //System.out.println(books.size());
    }

    /*public static void populateFakeBorrows(int qty, HashMap<String, Borrow> borrows) {

        // to-do: numb3r will be a limit, for security
        Borrow newBorrow;
        for (User user : users.values()) {
            //
            for (Book book : books.values()) {
                //
                newBorrow = new Borrow();
                String newBorrowId = Utils.createUUID();
                //
                newBorrow.setUser(user);
                newBorrow.setBook(book);
                newBorrow.setBorrowId(newBorrowId);
                newBorrow.setInitialBorrow(new Date());
                newBorrow.setDueDate(new Date());
                newBorrow.setBorrowStatus("CLOSED");

                borrows.put(newBorrowId, newBorrow);
                newBorrow = null;

            }
        }

        //Optional<User> optionalUser = users.values().stream().findFirst();
        //if (optionalUser.isPresent()) {}
        //    User user = optionalUser.get();
        for (User user : users.values()) {
            int i = 0;
            for (Book book : books.values()) {
                //

                newBorrow = new Borrow();
                String newBorrowId = Utils.createUUID();
                //
                newBorrow.setUser(user);
                newBorrow.setBook(book);
                newBorrow.setBorrowId(newBorrowId);
                newBorrow.setInitialBorrow(new Date());
                newBorrow.setDueDate(new Date());
                if (i % 2 == 0 ) newBorrow.setBorrowStatus("PROGRESS");
                else newBorrow.setBorrowStatus("DELAYED");

                borrows.put(newBorrowId, newBorrow);
                newBorrow = null;
                i++;
                if (i == 20) break;
            }
        }


    }*/

}
