package com.example.myFirstSpring.model;

//https://projectlombok.org/features/all
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
@Entity(name="Book")
@Table(name="BOOK_TABLE")
public class Book {
    @Id
    private String bookId;
    private String title;
    private String author;
    private int pages;
    private int publishedYear;
    private String isbn;

    private List<String> bookImageIds = new ArrayList<>();
    public void update(Book dataBook) {
        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> {
                    try {
                        Object value = field.get(dataBook);
                        return value != null &&
                                (!(value instanceof Number) || ((Number) value).intValue() != 0) &&
                                (!(value instanceof Boolean) || (Boolean) value);
                    } catch (IllegalAccessException e) {
                        // Handle exception
                        return false;
                    }
                })
                .forEach(field -> {
                    try {
                        field.set(this, field.get(dataBook));
                    } catch (IllegalAccessException e) {
                        // Handle exception
                    }
                });
    }
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

    public Book addBookImageId(String id) {
        this.getBookImageIds().add(id);
        return this;
    }
}