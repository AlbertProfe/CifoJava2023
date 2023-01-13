package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

        private String title;
        private int year;
        private List<Review> reviews = new ArrayList<Review>();

        public Movie(String title, int year) {
                this.title = title;
                this.year= year;

        }
}
