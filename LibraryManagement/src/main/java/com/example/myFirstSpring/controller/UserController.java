package com.example.myFirstSpring.controller;

import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.service.BookService;
import com.example.myFirstSpring.service.UserService;
import com.example.myFirstSpring.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @RequestMapping("/users")
    public String getAllUsers(Model model){
        // fetch all users, add to model
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("totalUsers", "Total users: " + userService.getAllUsers().size());
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

    @RequestMapping("/packedUserForm")
    public String packedUserForm(@RequestParam("idFromView") String id ,
                                 Model model){

        User userFound = userService.findUserById(id);

        if (userFound != null){
            model.addAttribute("userFromController", userFound);
            model.addAttribute("message", "User  found");}
        else
            model.addAttribute("message", "User not found");

        return "user/userToUpdateForm";
    }

    @PostMapping("/updateUser/{idFromView}")
    public String updateUser(@PathVariable("idFromView") String id,
                             User updatedUser) {

        User userFound = userService.findUserById(id);

        if (userFound != null) {
            userService.updateUserByUser(updatedUser);
            return "redirect:/user/users";
        } else return "user/userNotFound";


    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("idFromView") String id) {

        User userFound = userService.findUserById(id);

        if (userFound != null) {
            userService.deleteUserById(id);
            return "redirect:/user/users";
        } else return "user/userNotFound";


    }

    @RequestMapping("/detailUser")
    public String detailUser(@RequestParam("idFromView") String id, Model model) {

        User userFound = userService.findUserById(id);

        if (userFound != null) {
            model.addAttribute("user",userFound );
            return "/user/detailUser";
        } else return "user/userNotFound";


    }
    @RequestMapping("/books")
    public String booksToMakeBorrow(@RequestParam("idFromView") String id, Model model){

        User userFound = userService.findUserById(id);

        if (userFound != null) {
            model.addAttribute("books",bookService.getAllBooks() );
            return "/user/booksToSelect";
        } else return "user/userNotFound";


    }

    @RequestMapping("/createFakeUsers")
    public String createFakeUsers (@RequestParam("qty") int fakeusersnumber) {
        UserService.populateFakeUsers(fakeusersnumber);
        return "redirect:/user/users";
    }





}