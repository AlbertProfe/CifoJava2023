package org.example.view;


import org.example.manager.BorrowManager;
import org.example.utils.Utils;

import java.util.Scanner;
import static org.example.utils.Utils.askString;

public class UserInterface {

    private static BorrowManager BorrowManger;

    public static void start() {

        Scanner reader = new Scanner(System.in);
        Utils.populateFakeDataStorage();
        while (true) {
            mainMenu();
            // ask user what option choose
            // call ask static method and send two parameters
            // reader to object and string option
            String command = askString(reader, " Option?");
            if (command.equals("quit")) {
                break;
            } else if (command.equals("borrow")) {
                System.out.println("Make Borrow (borrow):");
                System.out.println(BorrowManager.createBorrow(reader));
            } else if (command.equals("return")) {
                System.out.println("Book Return (return):");
                bookReturnHandler(reader);
            } else if (command.equals("user")) {
                System.out.println("User Management (user)");
                userRequestHandler(reader);
            } else if (command.equals("book")) {
                System.out.println("Book Management (book)");
                bookRequestHandler(reader);
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    public static void userRequestHandler(Scanner reader){
        while (true) {
            userMenu();
            // ask user what option choose
            // call ask static method and send two parameters
            // reader to object and string option
            String command = askString(reader, "Option?");
            if (command.equals("quit")) {
                break;
            } else if (command.equals("create")) {
                System.out.println("Create user (create):");

            } else if (command.equals("list")) {
                System.out.println("List users (list)");

            } else if (command.equals("delete")) {
                System.out.println("Delete user (delete)");

            } else if (command.equals("update")) {
                System.out.println("Update user (update)");

            } else if (command.equals("get")) {
                System.out.println("Get one user (get)");

            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    public static void bookRequestHandler(Scanner reader){

        while (true) {
            bookMenu();
            // ask user what option choose
            // call ask static method and send two parameters
            // reader to object and string option
            String command = askString(reader, "Option?");
            if (command.equals("quit")) {
                break;
            } else if (command.equals("create")) {
                System.out.println("Create book (create):");

            } else if (command.equals("list")) {
                System.out.println("List books (list)");

            } else if (command.equals("delete")) {
                System.out.println("Delete book (delete)");

            } else if (command.equals("update")) {
                System.out.println("Update book (update)");

            } else if (command.equals("get")) {
                System.out.println("Get one book (get)");

            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    public static void bookReturnHandler(Scanner reader){
        while (true) {

            bookReturnMenu();;
            // ask user what option choose
            // call ask static method and send two parameters
            // reader to object and string option
            String command = askString(reader, "Option?");
            if (command.equals("quit")) {
                break;
            } else if (command.equals("book")) {
                System.out.println("Introduce book Id (book):");
                System.out.println(BorrowManager.returnBookByBook(reader));
            } else if (command.equals("user")) {
                System.out.println("Introduce user Id (user)");
                System.out.println(BorrowManager.returnBookByUser(reader));
            } else if (command.equals("borrow")) {
                System.out.println("Introduce borrow Id (borrow)");
                System.out.println(BorrowManager.returnBookByBorrow(reader));
            } else {
                System.out.println("Unknown command!");
            }
        }
    }


    public static void mainMenu(){
        System.out.println("\n Main Menu");
        System.out.println(" ---------");
        System.out.println("1 Make Borrow (borrow)");
        System.out.println("2 Book Return (return)");
        System.out.println("3 User Management (user)");
        System.out.println("4 Book Management (book)");
        System.out.println("5 Quit (quit)");

    }

    public static void userMenu(){
        System.out.println("\n User Menu");
        System.out.println(" ---------");
        System.out.println("1 Create user (create)");
        System.out.println("2 List users (list)");
        System.out.println("3 Delete user (delete)");
        System.out.println("4 Update user (update)");
        System.out.println("5 Get one user (get)");
        System.out.println("6 Quit (quit)");

        // UserClassifier.start();
        // our system is classifing User request
        // BookRequestHandler()
        // UserRequestHandler()
        // BorrowRequestHandler
        // BorrowRequestManger
        // askUser
        // optionUser
        // distributer
        // classifier
        // controller
        // manager
        //

    }

    public static void bookMenu(){
        System.out.println("\n Book Menu");
        System.out.println(" ---------");
        System.out.println("1 Create book (create)");
        System.out.println("2 List books (list)");
        System.out.println("3 Delete book (delete)");
        System.out.println("4 Update book (update)");
        System.out.println("5 Get one book (get)");
        System.out.println("6 Quit (quit)");

    }

    public static void bookReturnMenu(){
        System.out.println("\n Book Return Menu");
        System.out.println(" ---------");
        System.out.println("1 Introduce book Id (book)");
        System.out.println("2 Introduce user Id (user)");
        System.out.println("3 Introduce borrow Id (borrow)");
        System.out.println("4 Quit (quit)");

    }

}
