package org.example.manager;

import com.github.javafaker.Faker;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager {

    public static HashMap<String, User> users = new HashMap<>();

    public static void createUsers(int number) {
        // create faker object to use as
        // builder for user
        Faker faker = new Faker();
        User newUser;

        for (int i = 0; i < number; i++) {

            newUser =new User();

            String userId = Utils.createUUID();
            newUser.setUserId(userId);

            String userName = faker.name().firstName();
            newUser.setName(userName);

            String userAddress = faker.address().fullAddress();
            newUser.setAddress(userAddress);

            int userAge = faker.number().numberBetween(0, 100);
            newUser.setAge(userAge);

            List<Borrow> borrows = new ArrayList<>();
            newUser.setBorrows(borrows);

            users.put(userId, newUser);
            newUser = null;
        }
    }

}
