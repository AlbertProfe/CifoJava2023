package com.example.myFirstSpring.controller;

import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.service.BookService;
import com.example.myFirstSpring.service.BorrowService;
import com.example.myFirstSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @RequestMapping("/borrows")
    public String getAllUsers(Model model){
        // fetch all users, add to model
        model.addAttribute("borrows", borrowService.getAllBorrows());
        model.addAttribute("totalBorrows", "Total borrows: " + borrowService.getAllBorrows().size());
        return "borrow/borrows";
    }

    @RequestMapping("/findActiveBorrowByUserId")
    public String findActiveBorrowByUserId(Model model, @RequestParam("idFromView") String userId){
        // fetch all users, add to model
        model.addAttribute("borrows", borrowService.findBorrowByUserId("ACTIVE", userId));
        model.addAttribute("totalBorrows", "Total borrows: " + borrowService.findBorrowByUserId("ACTIVE",userId).size());
        model.addAttribute("userId", userId);

        return "borrow/borrows";
    }

    @RequestMapping("/findAllBorrowByUserId")
    public String findAllBorrowByUserId(Model model, @RequestParam("idFromView") String userId){
        // fetch all users, add to model
        model.addAttribute("borrows", borrowService.findBorrowByUserId("ALL",userId));
        model.addAttribute("totalBorrows", "Total borrows: " + borrowService.findBorrowByUserId("ALL",userId).size());
        model.addAttribute("userId", userId);

        return "borrow/borrows";
    }


    @RequestMapping ("/createBorrow")
    public String createBorrowByUserId(Model model){
        model.addAttribute("users", userService.getAllUsers().values());
        model.addAttribute("books",bookService.getAllBooks() );

        return "borrow/createBorrow";
    }
    @RequestMapping ("/createBorrowByUserIdAndBookIds")
    public String createBorrowByUserId(Model model,
                                       @RequestParam("selectedBooks") List<String> bookIds,
                                       @RequestParam("userIdFromSelect") String userId){

        User userFound = userService.findUserById(userId);

        String borrowDataConfirmation = borrowService.createBorrow(userId, bookIds);

        model.addAttribute("user", userFound);
        model.addAttribute("bookIds", bookIds);

        model.addAttribute("borrowDataConfirmation", borrowDataConfirmation);

        return "user/borrowConfirmation";
    }
    @RequestMapping("/returnBook")
    public String returnBookByBorrowId(Model model,
                                       @RequestParam("idFromView") String borrowId){

        String returnedBookStatus = borrowService.returnBookByBorrowId(borrowId);
        String userIdFound = borrowService.findUserIdByBorrowId(borrowId);

        return "redirect:findActiveBorrowByUserId" + "?idFromView=" + userIdFound;
    }
}


