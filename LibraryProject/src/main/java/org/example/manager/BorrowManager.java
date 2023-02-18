package org.example.manager;

import org.example.model.Book;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.utils.Utils;
import org.example.view.UserInterface;

import static org.example.manager.BookManager.books;
import static org.example.manager.UserManager.users;
import static org.example.utils.Utils.askString;
import java.util.*;
import java.util.stream.Collectors;

public class BorrowManager {

   public static HashMap< String , Borrow> borrows = new HashMap<>();

   public static void createFakeBorrows(int number) {
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
            if (book.getTitle().toLowerCase(Locale.ROOT).contains("the"))  newBorrow.setBorrowStatus("DELAYED");
            else newBorrow.setBorrowStatus("PROGRESS");

            borrows.put( newBorrowId, newBorrow);
            newBorrow = null;
         }
      }


   }

   public static String createFakeBorrow(Scanner reader){
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

   public static String returnBookByBook(Scanner reader) {
      Borrow borrowFound = null;

      String bookId = askString(reader, "Book id?");
      Book bookFound = books.getOrDefault(bookId, null);
      if (bookFound == null) {
         return "Book not found";
      } else {
         // find out borrow object by bookId
         borrowFound = findBorrowByBook(bookFound);
         if (borrowFound == null) return "Not borrow found with this bookId";
         borrowFound.setBorrowStatus("CLOSED");
         return "Your book borrow return is ok";
      }

   }

   public static String returnBookByUser(Scanner reader) {

   // find Borrow from borrows, if not error string
   Borrow borrowFound = null;
   // let s filter the 3 options
   String userId = askString(reader, "User id?");
   User userFound = users.getOrDefault(userId, null);
   //
   if (userFound == null) {
      return "User not found";
   } else {
      List<Borrow> borrowsByUser = findBorrowsByUser(userFound);
      int userBorrowsSize = borrowsByUser.size();
      //
      if (userBorrowsSize > 0) {
         List<Borrow> borrowsToClose = selectBorrowByUser(reader, borrowsByUser, userFound);
         //
         if (borrowsToClose.size() > 0) {
            for (Borrow borrow : borrowsToClose) {
               borrow.setBorrowStatus("CLOSED");
            }
            return "Your book borrow return is ok. You have closed " + borrowsToClose.size() + " books.";
         } else return "No borrows selected to close.";
         } else return "Not borrow found with this userId";
      //
      }
   }

   public static String returnBookByBorrow(Scanner reader) {
      Borrow borrowFound = null;
      //
      String borrowId = askString(reader, "Borrow id?");
      borrowFound = BorrowManager.borrows.getOrDefault(borrowId, null);
      if (borrowFound == null)  return "Borrow not found";

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

   public static List<Borrow> selectBorrowByUser(Scanner reader, List<Borrow> borrowsByUser, User user){
      //
      List<Borrow> selectedBorrows = new ArrayList<Borrow>();
      //
      System.out.println("\n" + user.getName() + " has " + borrowsByUser.size() + " borrows");
      int index = 1;
      for (Borrow borrow : borrowsByUser) {
         System.out.println("#" + index + " borrow Id: " + borrow.getBorrowId() +
                 ":\t"+ borrow.getBook().getTitle() + " - " + borrow.getBorrowStatus());
         index++;
      }
      //
      exitBorrowsSelection:
      while (true) {
         System.out.println("\nSelected borrows: " + selectedBorrows.size() + "\n ");
         //selectedBorrows.forEach(borrow -> System.out.println(borrow.getBorrowId() + " - " + borrow.getBook().getTitle()));
         for (Borrow borrow : selectedBorrows) {
            System.out.println(borrow.getBorrowId() + " - " + borrow.getBook().getTitle());
         }
         //
         UserInterface.selectBorrowMenu();
         String command = askString(reader, " Borrow Id??");
         //selectedBorrows = borrowsByUser.stream().filter(borrow -> borrow.getBorrowId().equals(command)).collect(Collectors.toList());
         //
         boolean borrowIdExists = false;
         for (Borrow borrow : borrowsByUser) {
            if (borrow.getBorrowId().equals(command)) {
               selectedBorrows.add(borrow);
               borrowIdExists = true;
               command = "borrowIdExists";
               break;
            }
         }
         //
         switch (command) {
            case "borrowIdExists":
               System.out.println("borrow Id is correct");
               break;
            case "close":
               break exitBorrowsSelection;
            case "quit":
               selectedBorrows.clear();
               break exitBorrowsSelection;
            case "all":
               selectedBorrows.addAll(borrowsByUser);
               break;
            case "clear":
               selectedBorrows.clear();
               break;
            default:
               System.out.println("Unknown command!");
               break;
         }
      }
      //
   return selectedBorrows;
   }

   public static List<Borrow> findBorrowsByUser(User user){
      //
      List<Borrow> borrowsByUser = new ArrayList<Borrow>();
      for(Borrow borrow: borrows.values()) {
         //
         String userIdFromBorrow = borrow.getUser().getUserId();
         String userIdFromUser = user.getUserId();
         boolean userIdCheck =  userIdFromBorrow.equals(userIdFromUser);
         boolean statusCheckProgress =  borrow.getBorrowStatus().equals("PROGRESS");
         boolean statusCheckDelayed =  borrow.getBorrowStatus().equals("DELAYED");
         //
         if (userIdCheck && (statusCheckProgress || statusCheckDelayed)) {
            borrowsByUser.add(borrow);
         }
      }

      return borrowsByUser;
   }

   public static List<Borrow> findAllBorrowsByUser(User user) {
      List<Borrow> borrowsByUser = new ArrayList<Borrow>();
      for(Borrow borrow: borrows.values()) {
         //
         String userIdFromBorrow = borrow.getUser().getUserId();
         String userIdFromUser = user.getUserId();
         boolean userIdCheck =  userIdFromBorrow.equals(userIdFromUser);
         //
         if (userIdCheck) { borrowsByUser.add(borrow); }
      }
      return borrowsByUser;
   }

   public static void printAllBorrows (){
      System.out.println("Total qty borrows: " +  borrows.values().size());
      borrows.values().forEach(borrow -> System.out.println(borrow.getBorrowId() + ": \n\t" +
                      borrow.getUser().getName() + " - " + borrow.getUser().getAge() +" - " +
                      borrow.getBook().getTitle() + " - " + borrow.getBorrowStatus() + " - " +
                      borrow.getDueDate()
      ));
   }

}