package com.example.myFirstSpring.controller;

import com.example.myFirstSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/users")
    public String getAllUsers(Model model){
        // fetch all users, add to model
        model.addAttribute("users", userService.getAllUsers());
        return "user/users";
    }

/*    @RequestMapping
    public String sendUserForm(){

        return "";
    }

    @RequestMapping
    public String createUser(){

        return "";
    }*/

    @RequestMapping("/createFakeUsers")
    public String createFakeUsers () {

        return "redirect:home";
    }





}