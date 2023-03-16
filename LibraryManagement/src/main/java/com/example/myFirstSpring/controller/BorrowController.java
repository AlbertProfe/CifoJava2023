package com.example.myFirstSpring.controller;

import com.example.myFirstSpring.service.BookService;
import com.example.myFirstSpring.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;


    @RequestMapping("/borrows")
    public String getAllUsers(Model model){
        // fetch all users, add to model
        model.addAttribute("borrows", borrowService.getAllBorrows());
        model.addAttribute("totalBorrows", "Total borows: " + borrowService.getAllBorrows().size());
        return "borrow/borrows";
    }


}
