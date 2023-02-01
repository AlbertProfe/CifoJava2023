package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Person{

    private String userId;
    private String address;
    private int age;
    private List<Borrow> borrows;
}
