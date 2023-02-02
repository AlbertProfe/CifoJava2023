package org.example.manager;

import com.github.javafaker.Faker;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.utils.InterfaceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager {
    public static HashMap<String, User> users = new HashMap<>();

    public static void createUsers(int number) {
        // create faker object to use as
        // builder for user
        Faker faker = new Faker();
        User newuser;

        for (int i = 0; i < number; i++) {

            newuser =new User();

            String userId = InterfaceUtils.createUUID();
            newuser.setUserId(userId);

            String userName = faker.name().firstName();
            newuser.setName(userName);

            String userAddress = faker.address().fullAddress();
            newuser.setAddress(userAddress);

            int userAge = faker.number().numberBetween(0, 100);
            newuser.setAge(userAge);

            List<Borrow> borrows = new ArrayList();
            newuser.setBorrows(borrows);

            users.put(userId, newuser);
            newuser = null;
        }
    }

}
