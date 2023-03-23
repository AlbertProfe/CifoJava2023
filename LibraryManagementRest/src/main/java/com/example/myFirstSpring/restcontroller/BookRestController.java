package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    @Autowired
    BookService bookService;

    @GetMapping ("/books")
    public HashMap<String, Book> getAllBooks(){

      return bookService.getAllBooks();


    }
}
