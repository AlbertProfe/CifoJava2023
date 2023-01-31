package org.example;

import org.example.manager.BookManager;
import org.example.model.Book;
import org.example.view.UserInterface;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class crudBookByConsoleTest {

    @Test
    public void startCreateTest(){

        String input = "Add\nRocky5\n1982\nGetAll\nQuit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        UserInterface.start();

        // get movieName from movies
        // from MovieManger access to movies Hashmap,
        // once in Hashmap get movie object value by key "Rocky"
        // in movie getTitle to obtain the title
        // this line will help to test my use-case
        String movieName = BookManager.movies.get("Rocky5").getTitle();
        //System.out.println(movieName);

        assertEquals("Rocky5", movieName);

        // Should we delete Rocky movie just created to be tested?
        //MovieManager.movies.remove("Rocky");
    }
    @Test
    public void startDeleteTest(){

        String input = "Add\nRocky\n1982\nGetAll\nDelete\nRocky\nQuit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        UserInterface.start();

        // get movieName from movies
        // from MovieManger access to movies Hashmap,
        // once in Hashmap get movie object value by key "Rocky"
        // this line will help to test my use-case
        Book book = BookManager.movies.getOrDefault("Rocky",null);
        //System.out.println(movieName);

        assertEquals(null, book);

        // Should we delete Rocky movie just created to be tested?
        //MovieManager.movies.remove("Rocky");
    }

    @Test
    public void createAndAddToStorageTest(){

        String input = "Rocky\n1982\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner reader = new Scanner(System.in);

        BookManager.createAndAddToStorage(reader);

        String movieName = BookManager.movies.get("Rocky").getTitle();
        //System.out.println(movieName);

        assertEquals("Rocky", movieName);
    }
}

