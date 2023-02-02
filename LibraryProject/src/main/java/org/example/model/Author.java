package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Author extends Person {

    public int publicationsQty;

    public List<Book> books;

    public Author(){
        this.books = new ArrayList<Book>();
    }
}
