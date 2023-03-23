package com.example.myFirstSpring.model;


//https://projectlombok.org/features/all
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments

public class Book {


    private String bookId;

    private String title;

    private String author;
    private int pages;

    private int publishedYear;

    private String isbn;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", authorName=" + author +
                ", pages=" + pages +
                ", publishedYear=" + publishedYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}