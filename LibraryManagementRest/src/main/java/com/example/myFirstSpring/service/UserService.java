package com.example.myFirstSpring.service;

import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.repository.UserRepository;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public static HashMap<String, User> users = new HashMap<>();

    static {
        Utils.populateFakeUsers(20, users);
    }

    public static void populateFakeUsers(int fakeusersnumber) {
        Utils.populateFakeUsers(fakeusersnumber, users);
    }

    public Iterable<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    public User createUser(User user) {
        String newUserId = Utils.createUUID();
        user.setUserId(newUserId);
        return userRepository.save(user);
    }



    public User createUser() {

        User newUser = new User();
        newUser.setAddress("");
        return newUser;
    }

    public Optional<User>  findUserById(String id) {

        Optional<User> foundUser = userRepository.findById(id);
        //users.getOrDefault(id, null);
        return foundUser;
    }

    public void updateUserByUser(User userFound) {
        users.put(userFound.getUserId(), userFound);
    }

    public void deleteUserById(String id) {
        //users.remove(id);
        userRepository.deleteById(id);


    }

    public void createBorrow() {

    }

    public User updateUser(String id, User dataUser) {
        //to do
        return null;
    }


}
