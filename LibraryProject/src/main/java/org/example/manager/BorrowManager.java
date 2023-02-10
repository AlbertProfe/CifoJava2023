package org.example.manager;

import org.example.model.Book;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.utils.InterfaceUtils;

import static org.example.utils.InterfaceUtils.askString;

import java.util.Date;
import java.util.HashMap;
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

      String borrowId = InterfaceUtils.createUUID();
      newBorrow.setBorrowId(borrowId);
      BorrowManager.borrows.put(borrowId,newBorrow );


      return "Your borrow is ok. Your borrowId is: " + borrowId;


   }
}
