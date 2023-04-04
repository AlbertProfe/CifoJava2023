/*
package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.model.Borrow;
import com.example.myFirstSpring.service.BookService;
import com.example.myFirstSpring.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowRestController {

    @Autowired
    BorrowService borrowService;

    @GetMapping("/borrows")
    public HashMap<String, Borrow> getAllBorrows(){
        return borrowService.getAllBorrows();
    }
    @GetMapping("/createBorrow")
    public Borrow createBorrow (@RequestParam("userId") String userId ,
                                @RequestParam("bookIds") List<String> bookIds){

        Borrow borrow = borrowService.createBorrow(userId, bookIds);

        return  borrow;
    }

    @GetMapping("/returnBorrow")
    public Borrow returnBook (@RequestParam("borrowId") String borrowId){

        //borrowService.returnBookByBorrowId();

        return null;
    }
}
*/
