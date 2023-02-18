package org.example.utils;

import org.example.manager.BookManager;
import org.example.manager.UserManager;
import org.example.manager.BorrowManager;
import java.util.Scanner;
import java.util.UUID;

public class Utils {

    public static String askString(Scanner reader, String question) {
        System.out.println(question);
        String result = reader.nextLine();

        return result;
    }

    public static int askInt(Scanner reader, String question) {
        System.out.println(question);
        int result = Integer.parseInt(reader.nextLine());
        return result;
    }

    public static String createUUID() {

        UUID uuid = UUID.randomUUID();
        //System.out.println("UUID generated ( version - " + uuid.version() + ") : " +  uuid);
        String id = uuid.toString();

        return id;
    }

    public static void populateFakeDataStorage() {
        BookManager.createFakeBooks(10);
        UserManager.createFakeUsers(10);
        BorrowManager.createFakeBorrows(1000);
        //
        printPopulateFakeDataStorage();

        // just the first element
        /* Optional<String> firstUserKey = UserManager.users.keySet().stream().findFirst();
        String firstUserId = "";
        if (firstUserKey.isPresent()) {
            firstUserId = firstUserKey.get();
            System.out.println("userId: " + firstUserId );
        }

        Optional<String> firstBookKey = BookManager.books.keySet().stream().findFirst();
        String firstBookId ="";
        if (firstBookKey.isPresent()) {
            firstBookId = firstBookKey.get();
            System.out.println("bookId: " + firstBookId );
        }*/
    }

    public static void printPopulateFakeDataStorage() {
        System.out.println("\nBookIds:\n" + BookManager.books.keySet());
        System.out.println("\nUserIds:\n" + UserManager.users.keySet());
        //System.out.println("\nBorrowsIds:\n" + BorrowManager.borrows.keySet());
    }
}
