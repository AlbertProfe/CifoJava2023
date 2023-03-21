package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.model.Borrow;
import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BorrowService {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    public static HashMap<String, Borrow> borrows = new HashMap<>();

    static {
        Utils.populateFakeBorrows(200, borrows);
    }

    public HashMap<String, Borrow> getAllBorrows() {
        return borrows;
    }

    public HashMap<String, Borrow> findBorrowByUserId(String query, String userid) {
        //
        HashMap<String, Borrow> borrowsByUser = new HashMap<>();
        for (Borrow borrow : borrows.values()) {
            //
            String userIdFromBorrow = borrow.getUser().getUserId();
            boolean userIdCheck = userIdFromBorrow.equals(userid);
            switch (query) {
                case "ACTIVE":
                    boolean ProgressStatusCheck = borrow.getBorrowStatus().equals("PROGRESS");
                    boolean DelayedStatusCheck = borrow.getBorrowStatus().equals("DELAYED");
                    if (userIdCheck && (ProgressStatusCheck || DelayedStatusCheck)) {
                        borrowsByUser.put(borrow.getBorrowId(), borrow);
                    }
                    break;
                    //
                case "ALL":
                    if (userIdCheck) {
                        borrowsByUser.put(borrow.getBorrowId(), borrow);
                    }
                    break;
                default:
                    String error = "";
            }
        }

        return borrowsByUser;
    }

    public String createBorrow(String userId, List<String> selectedBooksIds) {

        Date dueDate =  new Date();
        List<String> borrowsId = new ArrayList<String>();

        for (String bookId : selectedBooksIds){
            Borrow newBorrow = new Borrow();

            Book book = bookService.findBookById(bookId);
            if (book != null) newBorrow.setBook(book);
            else return "Book not found: " + bookId +  " Borrow cancelled";

            User user = userService.findUserById(userId);
            if (user != null) newBorrow.setUser(user);
            else return "User not found. Borrow cancelled";

            Date initialBorrowDate = new Date();
            newBorrow.setInitialBorrow(initialBorrowDate);

            newBorrow.setDueDate(dueDate);

            newBorrow.setBorrowStatus("PROGRESS");

            String borrowId = Utils.createUUID();
            newBorrow.setBorrowId(borrowId);
            borrowsId.add(borrowId);

            borrows.put(borrowId, newBorrow);

        }

        return "Your borrow is ok.\n Due Date: " + dueDate.toString() +
                "\n Borrow ticket: " + borrowsId.toString() ;

    }

    public String returnBookByBorrowId(String borrowId) {

        Borrow borrowFound = borrows.getOrDefault(borrowId, null);

        if (borrowFound != null) {
            borrowFound.setBorrowStatus("CLOSED");
            return "Book returned";
        } else {
            return "Borrow not found"; }
    }

    public String findUserIdByBorrowId(String borrowId) {
        Borrow borrowFound = borrows.getOrDefault(borrowId, null);
        String response;
        if (borrowFound != null) {
            response = borrowFound.getUser().getUserId();
        }else{
            response = "User not found";
        }
    return response;
    }
}



