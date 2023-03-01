package com.example.myFirstSpring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
public class User {
    private int userId;
    private String name;
    private String address;
    private int age;


}



