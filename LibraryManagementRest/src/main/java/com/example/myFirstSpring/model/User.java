package com.example.myFirstSpring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
@Entity(name="User")
@Table(name="USER_TABLE")
public class User {
    @Id
    private String userId;
    private String name;
    private String address;
    private int age;


}



