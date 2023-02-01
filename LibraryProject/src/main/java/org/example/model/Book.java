package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

        private String title;
        private String ISBN;
        private Author author;
        private int year;
        private List<Borrow> borrows;


        public Book(String title, int year) {
                this.title = title;
                this.year= year;

        }
}
