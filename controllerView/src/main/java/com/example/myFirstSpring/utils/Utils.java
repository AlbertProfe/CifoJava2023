package com.example.myFirstSpring.utils;


import com.example.myFirstSpring.model.Librarian;
import com.example.myFirstSpring.model.User;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static String createUUID() {

        UUID uuid = UUID.randomUUID();
        //System.out.println("UUID generated ( version - " + uuid.version() + ") : " +  uuid);
        String id = uuid.toString();

        return id;
    }

    public static HashMap<String, User> populateFakeUsers(int number, HashMap<String, User> users ) {
        // create faker object to use as
        // builder for user
        Faker faker = new Faker();
        User newUser;

        for (int i = 0; i < number; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1001, 9999 + 1);
            newUser = new User();

            String userId = Utils.createUUID();
            newUser.setUserId(userId);

            String userName = faker.name().firstName();
            newUser.setName(userName);

            String userAddress = faker.address().fullAddress();
            newUser.setAddress(userAddress);

            int userAge = faker.number().numberBetween(0, 100);
            newUser.setAge(userAge);

            users.put(userId, newUser);

        }
        //System.out.println(users.size());
        return  users;
    }

    public static HashMap<String, Librarian> populateFakeLibrarians(int number, HashMap<String, Librarian> librarians ) {
        // create faker object to use as
        // builder for user
        Faker faker = new Faker();
        Librarian newLibrarian;

        for (int i = 0; i < number; i++) {

            newLibrarian = new Librarian();

            String librarianId = Utils.createUUID();
            newLibrarian.setLibrarianId(librarianId);

            String userName = faker.name().firstName();
            newLibrarian.setName(userName);

            String userAddress = faker.address().fullAddress();
            newLibrarian.setAddress(userAddress);

            int userAge = faker.number().numberBetween(0, 100);
            newLibrarian.setAge(userAge);

            String position = faker.job().position();
            newLibrarian.setPosition(position);

            String seniority = faker.job().seniority();
            newLibrarian.setSeniority(seniority);

            double salary = faker.number().numberBetween(50000,100000);
            newLibrarian.setSalary(salary);

            librarians.put(librarianId, newLibrarian);

        }
        //System.out.println(users.size());
        return  librarians;
    }





}
