package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person extends Member implements GeneralOperations {

    public String name;

    @Override
    public void searchBooks() {

    }
}
