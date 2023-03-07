package com.example.myFirstSpring.controller;

import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.service.UserService;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/createUser")
    public String createUser(User user){

        userService.createUser(user);

        return "redirect:users";
        //return "user/userCreationResult";
    }

    @RequestMapping("/emptyForm")
    public String sendUserForm(){

        return "user/userForm";
    }

    @RequestMapping("/createFakeUsers")
    public String createFakeUsers (@RequestParam("qty") int fakeusersnumber) {

        Utils.populateFakeUsers(fakeusersnumber,userService.getAllUsers());

        return "redirect:home";
    }





}