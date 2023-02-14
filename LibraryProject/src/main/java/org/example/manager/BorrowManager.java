package org.example.manager;

import org.example.model.Book;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.utils.Utils;
import static org.example.utils.Utils.askString;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


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
         newBorrow.setBook(bookFound);
      }

      newBorrow.setDueDate(new Date());
      newBorrow.setInitialBorrow(new Date());

      newBorrow.setBorrowStatus("PROGRESS");

      String borrowId = Utils.createUUID();
      newBorrow.setBorrowId(borrowId);

      BorrowManager.borrows.put(borrowId,newBorrow );

      return "Your borrow is ok." +
              "\n\tYour borrowId is: " + borrowId +
              "\n\tBook Title: " + bookFound.getTitle() +
              "\n\tUser Name: " + userFound.getName() +
              "\n\tDue Date: " +  newBorrow.getDueDate();
   }

   public static String returnBook(String inputType,Scanner reader) {

      // find Borrow from borrows, if not error string
      Borrow borrowFound = null;

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

      // create String to return

      return "return";
   }

   public static Borrow findBorrowByBook(Book book){

      for(Borrow borrow: borrows.values()) {
         if (borrow.getBook().getBookId().equals(book.getBookId())) {
            return borrow;
         }
      }
      return null;
   }

   public static List<Borrow> findBorrowsByUser(User user){

      return null;
   }




}
