package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
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
    public ResponseEntity<User> deleteUser (@RequestParam("id") String id){
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation","deleteBook");
        headers.add("version","api 1.0");

        Optional<User> userFound  = userService.findUserById(id);
        boolean isUser = userFound.isPresent();
        if(isUser) {
            //Optional<Book> deletedBook = bookservice.deleteBookById(id);
            userService.deleteUserById(id);
            headers.add("operationStatus","deleted");
            return  ResponseEntity.accepted().headers(headers).body(userFound.get());
        } else {
            headers.add("operationStatus","not found");
            return ResponseEntity.accepted().headers(headers).body(null);}


    }

}
