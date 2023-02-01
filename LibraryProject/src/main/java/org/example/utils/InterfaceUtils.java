package org.example.utils;

import org.example.model.Person;
import org.example.model.User;

import java.util.Scanner;
import java.util.UUID;

public class InterfaceUtils {

    public static String askString (Scanner reader, String question){
        System.out.println(question);
        String result = reader.nextLine();

        return result;
    }

    public static int askInt (Scanner reader, String question){
        System.out.println(question);
        int result = Integer.parseInt(reader.nextLine());
        return result;
    }


    public static String createUUID () {

        UUID uuid = UUID.randomUUID();
        System.out.println("UUID generated - " + uuid);
        System.out.println("UUID Version - " + uuid.version());
        String id = uuid.toString();
        return id;

    }
}
