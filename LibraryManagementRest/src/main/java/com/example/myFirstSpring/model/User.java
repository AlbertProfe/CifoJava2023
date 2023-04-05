package com.example.myFirstSpring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.reflect.Field;
import java.util.Arrays;

@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
@Entity(name="User")
@Table(name="USER_TABLE")
public class User {
    @Id
    private String userId;
    private String name;
    private String address;
    private int age;

    public void update(User dataUser) {
        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> {
                    try {
                        Object value = field.get(dataUser);
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
                        field.set(this, field.get(dataUser));
                    } catch (IllegalAccessException e) {
                        // Handle exception
                    }
                });
    }

}



