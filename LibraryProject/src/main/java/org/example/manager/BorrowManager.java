package org.example.manager;

import org.example.model.Borrow;

import java.util.HashMap;
import java.util.HashSet;

public class BorrowManager {

   public static HashMap< String , Borrow> borrows = new HashMap<>();

   public static void createBorrow(){
      System.out.println("Introduce user and book Ids");
      // ask user for id
      // search id at hashmap users: users.get(id)
         // >> user object
         // >> no user at hashmap user

      // ask user for book id
      // search id at hashmap books: books.get(id)
         // >> book object
         // >> no book at hashmap books

      // create EMPTY borrow object
      // Borrow newborrow = new Borrow();

      // call to id method to create id
      // setId

      // new Date with today s date and add 15 days
      // setInitialBorrow and setDueDate

      //  leave returnDate

      // enums: "progress" "delayed" "closed"
      // newborrow.setStatus("progress")

      // setBook(book)
      // setUser(user)

      // add borrow object to hashmap borrows
      // borrows.put(id, newborrow)
   }
}
