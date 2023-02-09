package org.example.view;


import org.example.manager.BorrowManager;

import java.util.Scanner;
import static org.example.utils.InterfaceUtils.askString;

public class UserInterface {

    private static BorrowManager BorrowManger;

    public static void start() {

        Scanner reader = new Scanner(System.in);
        while (true) {
            mainMenu();
            // ask user what option choose
            // call ask static method and send two parameters
            // reader to object and string option
            String command = askString(reader, "Option?");
            if (command.equals("quit")) {
                break;
            } else if (command.equals("borrow")) {
                System.out.println("Make Borrow (borrow):");
                BorrowManger.createBorrow();
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

    public static void mainMenu(){
        System.out.println(" Main Menu");
        System.out.println(" ---------");
        System.out.println("1 Make Borrow (borrow)");
        System.out.println("2 User Management (user)");
        System.out.println("3 Book Management (book)");
        System.out.println("4 Quit (quit)");

    }

    public static void userMenu(){
        System.out.println(" User Menu");
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
        System.out.println(" Book Menu");
        System.out.println(" ---------");
        System.out.println("1 Create book (create)");
        System.out.println("2 List books (list)");
        System.out.println("3 Delete book (delete)");
        System.out.println("4 Update book (update)");
        System.out.println("5 Get one book (get)");
        System.out.println("6 Quit (quit)");

    }

}
