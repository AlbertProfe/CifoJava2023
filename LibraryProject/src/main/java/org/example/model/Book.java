package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

        private String bookId;
        private String ISBN;
        private String title;
        private int year;
        private Author author;
        private List<Borrow> borrows;


        public Book(String title, int year) {
                this.title = title;
                this.year= year;

        }
}
