package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.model.Borrow;
import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.repository.BorrowRepository;
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
    @Autowired
    BorrowRepository borrowRepository;

    public Iterable<Borrow> getAllBorrows(){
        //
        return  borrowRepository.findAll();
    }

    public List<Borrow> populateFakeBorrows(int qty){
        // to-do: numb3r will be a limit, for security
        Borrow newBorrow;
        Iterable<User> users = userService.getAllUsers();
        Iterable<Book> books = bookService.getAllBooks();
        long usersQty = users.spliterator().getExactSizeIfKnown();
        long booksQty = books.spliterator().getExactSizeIfKnown();

        List<Borrow> newBorrows = new ArrayList<>();
        for (User user : users) {
            int i = 0;
            for (Book book : books) {
                //
                newBorrow = new Borrow();
                String newBorrowId = Utils.createUUID();
                //
                newBorrow.setUserId(user.getUserId());
                newBorrow.setBookId(book.getBookId());
                newBorrow.setBorrowId(newBorrowId);
                newBorrow.setInitialBorrow(new Date());
                newBorrow.setDueDate(new Date());

                if ( (i >= 0 && i <= 10)) newBorrow.setBorrowStatus("CLOSED");
                else {
                    if (i % 2 == 0) newBorrow.setBorrowStatus("PROGRESS");
                    else newBorrow.setBorrowStatus("DELAYED");
                }

                borrowRepository.save(newBorrow);
                newBorrows.add(newBorrow);
                i++;
                if (i == qty) break;
            }
        }

        return newBorrows;
    }

    public HashMap<String, String> createBorrow(String userId, List<String> selectedBooksIds) {

        Borrow newBorrow = new Borrow();
        Date dueDate = new Date();
        HashMap<String, String>  borrows = new HashMap<>();
        List<String> borrowsId = new ArrayList<String>();

        for (String bookId : selectedBooksIds) {

            Optional<Book> book = bookService.findBookById(bookId);
            if (book.isPresent()) {
                newBorrow.setBookId(book.get().getBookId());
                //else return "Book not found: " + bookId +  " Borrow cancelled";
            } else {
                borrows.put("statusDescription", "borrowOperation failed, bookId not found: " + bookId);
                borrows.put("status" , "fail");
                return borrows;
            }

            Optional<User> user = userService.findUserById(userId);
            if (user.isPresent()) {
                newBorrow.setUserId(user.get().getUserId());
                //else return "User not found. Borrow cancelled";
            } else {
                borrows.put("statusDescription" , "borrowOperation failed, userId not found: " + userId);
                borrows.put("status" , "fail");
                return borrows;
            }

            Date initialBorrowDate = new Date();
            newBorrow.setInitialBorrow(initialBorrowDate);

            newBorrow.setDueDate(dueDate);

            newBorrow.setBorrowStatus("PROGRESS");

            String borrowId = Utils.createUUID();
            newBorrow.setBorrowId(borrowId);


            borrows.put(borrowId, newBorrow.toString());
            borrowsId.add(borrowId);
            borrows.put("statusDescription" , "borrowOperation success, booksIds ( " + borrowsId.size() + " ):" + borrowsId.toString());
            borrows.put("status" , "success");
            borrowRepository.save(newBorrow);

        }
        return borrows;
    }
}

/*public Borrow createBorrow(String userId, List<String> selectedBooksIds) {

Borrow newBorrow = new Borrow();
Date dueDate = new Date();
List<String> borrowsId = new ArrayList<String>();

for (String bookId : selectedBooksIds) {


    Book book = bookService.findBookById(bookId);
    if (book != null) newBorrow.setBook(book);
        //else return "Book not found: " + bookId +  " Borrow cancelled";
    else return null;

    Optional<User> user = userService.findUserById(userId);
    if (user.isPresent()) newBorrow.setUser(user.get());
        //else return "User not found. Borrow cancelled";
    else return null;

    Date initialBorrowDate = new Date();
    newBorrow.setInitialBorrow(initialBorrowDate);

    newBorrow.setDueDate(dueDate);

    newBorrow.setBorrowStatus("PROGRESS");

    String borrowId = Utils.createUUID();
    newBorrow.setBorrowId(borrowId);
    borrowsId.add(borrowId);

    borrows.put(borrowId, newBorrow);

}
}*/

/*public HashMap<String, Borrow> findBorrowByUserId(String query, String userid) {
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
    }*/



    /*return "Your borrow is ok.\n Due Date: " + dueDate.toString() +
                "\n Borrow ticket: " + borrowsId.toString() ;*//*

        return newBorrow;
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



*/