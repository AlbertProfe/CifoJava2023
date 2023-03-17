package com.example.myFirstSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.myFirstSpring.service.BookService;
import java.util.Date;
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    public String getAllBooks(Model model){

        model.addAttribute("todayDate", new Date().toString());
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("totalBooks", "Total books: " +  bookService.getAllBooks().size());


        return "book/books";
    }



}
