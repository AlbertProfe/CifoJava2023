package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.Librarian;
import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LibrarianService {

    public static HashMap<String, Librarian> librarians = new HashMap<>();

    static {
        Utils.populateFakeLibrarians (20, librarians);
    }

    public HashMap<String, Librarian> getAllLibrarians (){

        return librarians;
    }

}
