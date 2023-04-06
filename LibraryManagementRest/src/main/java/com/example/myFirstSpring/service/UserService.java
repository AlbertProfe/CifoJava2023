package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.repository.UserRepository;
import com.example.myFirstSpring.utils.Utils;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    public User createUser(User user) {
        String newUserId = Utils.createUUID();
        user.setUserId(newUserId);
        return userRepository.save(user);
    }

    public Optional<User> findUserById(String id) {
        Optional<User> foundUser = userRepository.findById(id);
        //users.getOrDefault(id, null);
        return foundUser;
    }

    public void deleteUserById(String id) {
        //users.remove(id);
        userRepository.deleteById(id);
    }


    public User updateUser(User userFound, User dataUser) {
        userFound.update(dataUser);
        return userRepository.save(userFound);
    }

    public HashMap<String, User> populateFakeUsers(int number) {
        // create faker object to use as
        // builder for user
        HashMap<String, User> users =  new HashMap<>();
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
            // be careful: it is just a PLACEHOLDER
            //newUser.getBorrowIds().add("00000000-0000-0000-0000-00000000000");

            users.put(userId, newUser);
            userRepository.save(newUser);
        }

        return users;
        //System.out.println(users.size());
    }


    public void addBorrowId(User user, String borrowId) {
        user.getBorrowIds().add(borrowId);
    }
}
