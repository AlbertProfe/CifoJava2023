package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/deleteBook")
    public String deletebook (@RequestParam("id") String id){

        Book book = bookService.deleteBook(id);

        if (book != null){
            return "Book deleted by Service properly.\nBook deleted: " + book.toString();
        } else return "error 418";


    }

}
