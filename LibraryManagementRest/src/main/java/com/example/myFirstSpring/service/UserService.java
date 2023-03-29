package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class UserService {
    public static HashMap<String, User> users = new HashMap<>();

    static {
        Utils.populateFakeUsers(20, users);
    }

    public static void populateFakeUsers(int fakeusersnumber) {
        Utils.populateFakeUsers(fakeusersnumber, users);
    }

    public User createUser(User user) {

        String newUserId = Utils.createUUID();
        user.setUserId(newUserId);
        return users.put(newUserId, user);
    }

    public HashMap<String, User> getAllUsers() {

        return users;
    }

    public User createUser() {

        User newUser = new User();
        newUser.setAddress("");


        return newUser;
    }

    public User findUserById(String id) {
        return users.getOrDefault(id, null);
    }

    public void updateUserByUser(User userFound) {
        users.put(userFound.getUserId(), userFound);
    }

    public User deleteUserById(String id) {
       return  users.remove(id);
    }

    public void createBorrow() {

    }

    public User updateUser(String id, User dataUser) {
        //to do
        return null;
    }
}
