package org.example.utils;

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
        //System.out.println("UUID generated ( version - " + uuid.version() + ") : " +  uuid);
        String id = uuid.toString();
        return id;

    }
}
