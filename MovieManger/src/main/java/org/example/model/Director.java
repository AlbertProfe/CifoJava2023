package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Director extends Person {

    public int oscarQty;
    public List<Movie> movies;

    public Director (){
        this.movies = new ArrayList<Movie>();
    }
}
