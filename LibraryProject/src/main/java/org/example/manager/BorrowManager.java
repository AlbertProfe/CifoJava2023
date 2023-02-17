package org.example.manager;

import org.example.model.Book;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.utils.Utils;
import static org.example.manager.BookManager.books;
import static org.example.manager.UserManager.users;
import static org.example.utils.Utils.askString;
import java.util.*;

public class BorrowManager {

   public static HashMap< String , Borrow> borrows = new HashMap<>();

   public static void createBorrows(int number) {
      // to-do: numb3r will be a limit, for security
      Borrow newBorrow;
      for(User user: users.values()) {
         //
         for(Book book: books.values()) {
         //
         newBorrow = new Borrow ();
         String newBorrowId = Utils.createUUID();
         //
         newBorrow.setUser(user);
         newBorrow.setBook(book);
         newBorrow.setBorrowId(newBorrowId);
         newBorrow.setInitialBorrow(new Date());
         newBorrow.setDueDate(new Date());
         newBorrow.setBorrowStatus("CLOSED");

         borrows.put( newBorrowId, newBorrow);
         newBorrow = null;

         }
      }
      Optional<User> optionalUser = UserManager.users.values().stream().findFirst();
      if (optionalUser.isPresent()) {
         User user = optionalUser.get();
         for(Book book: books.values()) {
            //
            newBorrow = new Borrow ();
            String newBorrowId = Utils.createUUID();
            //
            newBorrow.setUser(user);
            newBorrow.setBook(book);
            newBorrow.setBorrowId(newBorrowId);
            newBorrow.setInitialBorrow(new Date());
            newBorrow.setDueDate(new Date());
            newBorrow.setBorrowStatus("PROGRESS");

            borrows.put( newBorrowId, newBorrow);
            newBorrow = null;
         }
      }


   }

   public static String createBorrow(Scanner reader){
      Borrow newBorrow = new Borrow();

      System.out.println("Introduce user and book Ids");
      String bookId = askString(reader, "Book id?");

      Book bookFound = books.getOrDefault(bookId, null);
      if (bookFound==null) {
         return "Book not found";
      } else {
         newBorrow.setBook(bookFound);
      }

      String userId = askString(reader, "User id?");
      User userFound = users.getOrDefault(userId, null);
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
         /*
         borrowsByUser.forEach( (borrow) -> {
                 System.out.println("borrow Id: " + borrow.getBorrowId() + ":\n\t"+ borrow + "\n");
                  borrowIds.add(borrow.getBorrowId());
         });
         */
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

}