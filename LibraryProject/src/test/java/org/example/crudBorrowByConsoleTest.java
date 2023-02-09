package org.example;

import org.example.manager.BorrowManager;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.view.UserInterface;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class crudBorrowByConsoleTest {

    @Test
    public void createBorrowTest (){

    }

    @Test
    public void createInputByConsoleBorrowTest (){

        // string input with yes to borrow, select book and user
        // proceed yes and borrow done
        String input = "Borrow\n159742OPI\nAnna Karenina\nYes\nQuit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        UserInterface.start();

        //Borrow borrow = BorrowManager.borrows.;
        //assertEquals with new object Borrow created
        // see if it exists in hashmap

    }
}
