package com.example.myFirstSpring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Borrow")
@Table(name="BORROW_TABLE")
public class Borrow {
    @Id
    private String borrowId;
    private Date initialBorrow;
    private Date dueDate;
    private Date returnDate;
    private String borrowStatus;
    //private Book book;
    //private User user;
    private String bookId;
    private String userId;




}
