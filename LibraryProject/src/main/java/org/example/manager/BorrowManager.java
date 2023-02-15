package org.example.manager;

import org.example.model.Book;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.utils.Utils;

import static org.example.utils.Utils.askInt;
import static org.example.utils.Utils.askString;

import java.util.*;


public class BorrowManager {

   public static HashMap< String , Borrow> borrows = new HashMap<>();

   public static String createBorrow(Scanner reader){
      Borrow newBorrow = new Borrow();

      System.out.println("Introduce user and book Ids");
      String bookId = askString(reader, "Book id?");

      Book bookFound = BookManager.books.getOrDefault(bookId, null);
      if (bookFound==null) {
         return "Book not found";
      } else {
         newBorrow.setBook(bookFound);
      }

      String userId = askString(reader, "User id?");
      User userFound = UserManager.users.getOrDefault(userId, null);
      if (userFound==null) {
         return "User not found";
      } else {
        newBorrow.setUser(userFound);
      }

      newBorrow.setDueDate(new Date());
      newBorrow.setInitialBorrow(new Date());

      newBorrow.setBorrowStatus("PROGRESS");

      String borrowId = Utils.createUUID();
      newBorrow.setBorrowId(borrowId);

      BorrowManager.borrows.put(borrowId,newBorrow );

      // let s add this borrow to the user
      // userfound.getBorrows.add(newBorrow);

      return "Your borrow is ok." +
              "\n\tYour borrowId is: " + borrowId +
              "\n\tBook Title: " + bookFound.getTitle() +
              "\n\tUser Name: " + userFound.getName() +
              "\n\tDue Date: " +  newBorrow.getDueDate();
   }

   public static String returnBook(String inputType,Scanner reader) {

      // find Borrow from borrows, if not error string
      Borrow borrowFound = null;
      // let s filter the 3 options
      if (inputType.equals("book")) {
         String bookId = askString(reader, "Book id?");
         Book bookFound = BookManager.books.getOrDefault(bookId, null);
         if (bookFound==null) {
            return "Book not found";
         } else {
            // find out borrow object by bookId
            borrowFound = findBorrowByBook(bookFound);
            if (borrowFound == null) return "Not borrow found with this bookId";
         }
      } else if (inputType.equals("user")) {
         String userId = askString(reader, "User id?");
         User userFound = UserManager.users.getOrDefault(userId, null);
         if (userFound==null) {
            return "User not found";
         } else {
            // find out borrow object by userId
            borrowFound = findBorrowByUser(reader, userFound);
         }
      } else if (inputType.equals("borrow")) {
         String borrowId = askString(reader, "Borrow id?");
         borrowFound = BorrowManager.borrows.getOrDefault(borrowId, null);
         if (borrowFound==null) {
            return "Borrow not found";
         } else {
            // get borrow object
         }
      } else {
         return "Error. Please talk with Sys Admin. Before that refresh system.";
      }

      // once borrow object is found > change status
      borrowFound.setBorrowStatus("CLOSED");

      String messageReturnBook = "Your book borrow return is ok";

      return messageReturnBook;
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
   public static Borrow findBorrowByUser(Scanner reader, User user){
      //
      List<Borrow> borrowsByUser = findBorrowsByUser(user);
      //
      System.out.println(borrowsByUser);
      int index = askInt(reader,"Number (int) from 0?");
      Borrow borrowFound = borrowsByUser.get(index);

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




}
