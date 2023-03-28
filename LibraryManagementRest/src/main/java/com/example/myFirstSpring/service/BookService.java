package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.util.Arrays;

@Service
public class BookService {
    public static HashMap<String, Book> books = new HashMap<>();

    static {
        Utils.populateFakeBooks(200, books);
    }

    public HashMap<String, Book> getAllBooks (){

        return books;
    }

    public Book findBookById(String id) {
        return books.getOrDefault(id, null);
    }

    public Book deleteBook(String id) {

        Book bookToDelete = findBookById(id);

        if (bookToDelete != null){
            books.remove(id);
            return  bookToDelete;}
        else return null;

    }

    public Book createBook(Book book) {

        String bookId = Utils.createUUID();
        book.setBookId(bookId);

        return books.put(bookId, book);
    }

    public Book updateBook (String bookId, Book dataBook){

        Book bookToUpdate = findBookById(bookId);

        if (bookToUpdate != null)  {
            bookToUpdate.update(dataBook);
            return  books.replace(bookId, bookToUpdate);
        }
        else return null;

    }

}
