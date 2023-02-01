package org.example.view;

import org.example.manager.BookManager;
import java.util.Scanner;
import static org.example.utils.InterfaceUtils.askString;

public class UserInterface {

    public static void start() {

        Scanner reader = new Scanner(System.in);
        while (true) {
            menu();
            // ask user what option choose
            // call ask static method and send two parameters
            // reader to object and string option
            String command = askString(reader, "Option?");
            if (command.equals("Quit")) {
                break;
            } else if (command.equals("Add")) {
                System.out.println("Add Movie to database:");
                BookManager.createAndAddToStorage(reader);

            } else if (command.equals("Delete")) {
                System.out.println("We are going to delete ... wait");
                BookManager.deleteMovie(reader);

            } else if (command.equals("GetOne")) {
                System.out.println("We are going to search ... wait");
                BookManager.findMovie(reader);

            } else if (command.equals("Update")) {
                System.out.println("We are going to update ... wait");
                BookManager.updateMovie(reader);

            } else if (command.equals("GetAll")) {
                System.out.println("We are going to show all films ... wait");
                BookManager.getAllMoviesFromStorage();

            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    public static void menu(){
        System.out.println("1- Add");
        System.out.println("2- Delete");
        System.out.println("3- GetOne");
        System.out.println("4- GetAll");
        System.out.println("5- Update");
        System.out.println("6- Quit");

    }

}
