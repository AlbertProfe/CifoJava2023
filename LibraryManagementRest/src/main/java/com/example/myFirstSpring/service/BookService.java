package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.repository.BookRepository;
import com.example.myFirstSpring.utils.Utils;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Iterable<Book> getAllBooks() {

        Iterable<Book> books = bookRepository.findAll();

        return books;
    }

    public Book createBook(Book book) {

        Book bookCreated = bookRepository.save(book);

        return bookCreated;
    }

    public Optional<Book> findBookById(String id) {

        return bookRepository.findById(id);
    }

    public Optional<Book> findBookByTitle(String title) {
        //return bookRepository.findBookByTitle(title);
        return null;
    }

    public Book deleteBookByTitle(String title) {
        //Find out IF this id-book IS in our DB
        //Optional<Book> deletedBook = bookRepository.deleteBookByTitle(title);
        //
        return null;
    }

    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public  HashMap<String, Book> populateFakeBooks(int number) {
        HashMap<String, Book> fakeBooks = new HashMap<>();
        Faker faker = new Faker();
        Book newBook;

        for (int i = 0; i < number; i++) {

            newBook = new Book();

            String bookId = Utils.createUUID();
            newBook.setBookId(bookId);

            String title = faker.book().title();
            newBook.setTitle(title);

            String isbn = Utils.createISBN();
            newBook.setIsbn(isbn);

            String author = faker.book().author();
            newBook.setAuthor(author);


            int pages = faker.number().numberBetween(250, 999);
            newBook.setPages(pages);

            int publishedYear = faker.number().numberBetween(1800, 2020);
            newBook.setPublishedYear(publishedYear);

            bookRepository.save(newBook);
            fakeBooks.put(bookId,newBook);
        }
        return fakeBooks;
    }
}

