package com.example.myFirstSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
public class Librarian {
    private String librarianId;
    private String name;
    private String address;
    private int age;
    private String position;
    private String seniority;
    private double salary;


}



