package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Borrow;
import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public HashMap<String, User> getAllUsers(){
        return userService.getAllUsers();
    }
}
