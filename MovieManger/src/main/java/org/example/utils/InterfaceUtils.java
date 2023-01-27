package org.example.utils;

import java.util.Scanner;

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
}
