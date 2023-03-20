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

}
/*
    public static String returnBook(String inputType,Scanner reader) {

        // find Borrow from borrows, if not error string
        Borrow borrowFound = null;
        // let s filter the 3 options
        switch (inputType) {
            case "book":
                String bookId = askString(reader, "Book id?");
                Book bookFound = books.getOrDefault(bookId, null);
                if (bookFound == null) {
                    return "Book not found";
                } else {
                    // find out borrow object by bookId
                    borrowFound = findBorrowByBook(bookFound);
                    if (borrowFound == null) return "Not borrow found with this bookId";
                }
                break;
            case "user":
                String userId = askString(reader, "User id?");
                User userFound = users.getOrDefault(userId, null);
                if (userFound == null) {
                    return "User not found";
                } else {
                    // find out borrow object by userId
                    borrowFound = pickBorrowByUser(reader, userFound);
                    if (borrowFound == null) return "Not borrow found with this userId";
                }
                break;
            case "borrow":
                String borrowId = askString(reader, "Borrow id?");
                borrowFound = BorrowManager.borrows.getOrDefault(borrowId, null);
                if (borrowFound == null)  return "Borrow not found";
                break;
            default:
                return "Error 604. Please talk with Sys Admin. Keep calm and going on.";
        }

        // once borrow object is found > change status
        borrowFound.setBorrowStatus("CLOSED");

        return "Your book borrow return is ok";
    }

    public static Borrow findBorrowByBook(Book book){

        for(Borrow borrow: borrows.values()) {
            //
            String bookIdFromBorrow = borrow.getBook().getBookId();
            String bookIdFromBook = book.getBookId();
            boolean bookIdCheck =  bookIdFromBorrow.equals(bookIdFromBook);
            boolean statusCheck =  borrow.getBorrowStatus().equals("PROGRESS");

            if (bookIdCheck && statusCheck) {
                return borrow;
            }
        }
        return null;
    }

    public static Borrow pickBorrowByUser(Scanner reader, User user){
        Borrow borrowFound = null;
        //
        List<Borrow> borrowsByUser = findBorrowsByUser(user);
        int userBorrowsSize = borrowsByUser.size();
        //
        if (userBorrowsSize > 0) {
            System.out.println(user.getName() + " has " + userBorrowsSize + " borrows");
            List<String> borrowIds = new ArrayList<String>();
            //
         */
/*
         borrowsByUser.forEach( (borrow) -> {
                 System.out.println("borrow Id: " + borrow.getBorrowId() + ":\n\t"+ borrow + "\n");
                  borrowIds.add(borrow.getBorrowId());
         });
         *//*

            //
            int index = 1;
            for (Borrow borrow : borrowsByUser) {
                System.out.println("borrow #" + index + ": Id: " + borrow.getBorrowId() +
                        " \tBook: "+ borrow.getBook().getTitle() + " \tStatus: " +
                        borrow.getBorrowStatus());
                borrowIds.add(borrow.getBorrowId());
                index++;
            }
            //
            while (true) {
                System.out.println("\n1 - Borrow Id (borrowId)");
                System.out.println("2- Quit (quit) ");
                String command = askString(reader, " Borrow Id??");

                if (command.equals("quit")) {
                    break;
                } else if (borrowIds.contains(command)) {
                    //
                    for (Borrow borrow : borrowsByUser) {
                        if (borrow.getBorrowId().equals(command)) borrowFound = borrow;
                        // return borrowFound = borrow;
                        // as soon this line is executed, and we had found the book
                        // we should get out from the loop for the
                        // maybe a return would be a good option
                    }
                } else {
                    System.out.println("Unknown command!");
                }
            }
        } else return null;

        //return null;
        return borrowFound;
    }

    public static List<Borrow> findBorrowsByUser(User user){
        //
        List<Borrow> borrowsByUser = new ArrayList<Borrow>();
        for(Borrow borrow: borrows.values()) {
            //
            String userIdFromBorrow = borrow.getUser().getUserId();
            String userIdFromUser = user.getUserId();
            boolean userIdCheck =  userIdFromBorrow.equals(userIdFromUser);
            boolean statusCheck =  borrow.getBorrowStatus().equals("PROGRESS");
            //
            if (userIdCheck && statusCheck) {
                borrowsByUser.add(borrow);
            }
        }

        return borrowsByUser;
    }

    public static List<Borrow> findAllBorrowsByUser(User user) {
        return null;
    }
*/



