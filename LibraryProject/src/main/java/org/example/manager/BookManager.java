package org.example.manager;

import com.github.javafaker.Faker;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Borrow;
import org.example.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BookManager {

    public static HashMap<String, Book> books = new HashMap<String, Book>();

    public static void createBooks(int number) {
        // create faker object to use as
        // builder for book
        Faker faker = new Faker();
        Book newBook;

        for (int i = 0; i < number; i++) {

            newBook = new Book();

            // some people get nervous with this
            // be careful ...
            String bookId = Utils.createUUID();
            newBook.setBookId(bookId);

            String bookISBN = Utils.createUUID();
            newBook.setISBN(bookISBN);
            String bookTitle = faker.book().title();
            newBook.setTitle(bookTitle);

            int bookYear = faker.number().numberBetween(1000, 2023);
            newBook.setYear(bookYear);

            List<Borrow> borrows = new ArrayList();
            newBook.setBorrows(borrows);

            Author newAuthor = new Author();
            newBook.setAuthor(newAuthor);

            books.put( bookId, newBook);
            newBook = null;
        }
    }
    // Static method
    public static void createAndAddToStorage(Scanner reader) {
        // ask user for Book parameters
        String bookTitle = Utils.askString(reader, "Book name?");
        int bookYear = Utils.askInt(reader, "Book year?");
        String ISBN = Utils.askString(reader, "Book ISBN?");
        // create Movie object
        Book myBook = new Book();
        myBook.setTitle(bookTitle);
        myBook.setYear(bookYear);
        myBook.setISBN(ISBN);
        String bookId = Utils.createUUID();
        myBook.setBookId(bookId);
        //add to hashmap
        books.put(bookId, myBook);
        // return result to view
        System.out.println("Book added ...");
    }

    // Static method
    public static void deleteBook(Scanner reader) {
        // ask user for Movie to delete
        String bookToDelete =  Utils.askString(reader,"Which one to delete (bookId)?");
        // remove object from movies
        books.remove(bookToDelete);
        System.out.println("Book deleted ...");
    }

    // Static method
    public static void updateBook(Scanner reader) {
        // copy/paste from findMovie
        String bookIdToUpdate =  Utils.askString(reader,"Which one to update (bookId)?");
        Book bookFound = books.get(bookIdToUpdate);
        System.out.println("Book: " + bookFound);
        // copy/paste from createAndAddToStorage
        int bookYear = Utils.askInt(reader, "Book year?");
        // let s update
        Book bookToUpdate = new Book();
        bookToUpdate.setTitle(bookFound.getTitle());
        bookToUpdate.setYear(bookYear);
        // let s replace on movies hashmap
        books.replace(bookFound.getBookId(), bookToUpdate);
        System.out.println("Book updated ...");
    }

    // Static method
    public static void getAllBooksFromStorage() {
        // print all movies
        System.out.println(books);
    }

    // Static method
    public static void findBook(Scanner reader) {
        // ask user for movie
        String bookIdToFind =  Utils.askString(reader,"Which one to find (bookId)?");
        // get movie from movies by key
        Book bookFound = books.get(bookIdToFind);
        // print movie
        System.out.println("Book: " + bookFound);
    }

}
