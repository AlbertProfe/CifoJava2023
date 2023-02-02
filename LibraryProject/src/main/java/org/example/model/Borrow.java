package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {

    private String borrowId;
    private Date initialBorrow;
    private Date dueDate;
    private Date returnDate;
    private String borrowStatus;
    private Book book;
    private User user;



}
