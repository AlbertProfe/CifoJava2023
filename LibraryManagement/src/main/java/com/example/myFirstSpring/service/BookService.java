package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.stereotype.Service;
import java.util.HashMap;

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


}
