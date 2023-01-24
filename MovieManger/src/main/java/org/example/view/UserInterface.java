package org.example.view;

import org.example.manager.MovieManager;
import java.util.Scanner;

public class UserInterface {

    static Scanner reader = new Scanner(System.in);
    //MovieManager movieManager = new MovieManager();

    public static void start() {
        while (true) {
            // ask user what option choose
            // call ask static method and send two parameters
            // reader to object and string option
            String command = ask(reader, "Option?");
            if (command.equals("Quit")) {
                break;
            } else if (command.equals("Add")) {
                System.out.println("Add Movie to database:");
                MovieManager.createMovie(reader);
            } else if (command.equals("Delete")) {
                System.out.println("We are going to delete ... wait");
                //delete(scanner,movieManager);
            } else if (command.equals("Get")) {
                System.out.println("We are going to search ... wait");
                //get(scanner,movieManager);
            } else if (command.equals("Update")) {
                System.out.println("We are going to update ... wait");
                //update(scanner,movieManager);
            } else if (command.equals("Showall")) {
                System.out.println("We are going to show all films ... wait");
                // showall(scanner,movieManager);
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    public static String ask (Scanner reader, String question){
        // to-do
        // this static method will go
        // to a new DOMAIN: utils
        System.out.println(question);
        String result = reader.nextLine();

        return result;
    }

}
