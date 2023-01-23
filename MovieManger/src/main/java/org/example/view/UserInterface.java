package org.example.view;

import org.example.manager.MovieManager;

import java.util.Scanner;

public class UserInterface {

    Scanner reader = new Scanner(System.in);
    MovieManager movieManager = new MovieManager();

    while (true) {
            // ask to user what option choose
        // call ask static method and send two parameters
        // reader to object and string option
        String command = ask(reader);
        if (command.equals("Quit")) {
            break;
        } else if (command.equals("Add")) {
            movieManager.createMovie(reader);
        } else if (command.equals("Delete")) {
            System.out.println("ADSFADS");
            //delete(scanner,movieManager);
        } else if (command.equals("Get")) {
            System.out.println("ADSFADS");
            //get(scanner,movieManager);
        } else if (command.equals("Update")) {
            System.out.println("ADSFADS");
            //update(scanner,movieManager);
        } else if (command.equals("Showall")) {
            System.out.println("ADSFADS");
           // showall(scanner,movieManager);
        } else {
            System.out.println("Unknown command!");
        }
    }

    public static String ask (Scanner reader){
            // to-do
        System.out.println("Tell me which option");
        String result = reader.nextLine();

        return result;
    }

}
