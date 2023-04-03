package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.model.Borrow;
import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/h2users")
    public Iterable<User> getAllH2Users(){
        return userService.getAllH2Users();
    }

    @PostMapping("/createUser")
    public String createBook (@RequestBody User user){

        User userToCreate = userService.createUser(user);

        return "User created.\nUser:" + user.toString();
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser (@PathVariable String id, @RequestBody User dataUser) {


        User userUpdated = userService.updateUser(id,dataUser);

        if (userUpdated != null)
            return "User updated by Service properly.\nUser updated: " + userUpdated.toString();
        else return "error 418";

    }


    @DeleteMapping("/deleteUser")
    public String deleteUser (@RequestParam("id") String id){

        User user = userService.deleteUserById(id);

        if (user != null){
            return "User deleted by Service properly.\nUser deleted: " + user.toString();
        } else return "error 418";


    }

}
