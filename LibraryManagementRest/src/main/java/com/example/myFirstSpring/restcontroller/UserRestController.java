package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Book;
import com.example.myFirstSpring.model.User;
import com.example.myFirstSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("populate")
    public ResponseEntity<HashMap<String, User>> findBookById(@RequestParam("qty") int qty) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "populate");
        headers.add("version", "api 1.0");

        HashMap<String, User> fakeUsers = userService.populateFakeUsers(qty);

        if (fakeUsers.size() > 0 ) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(fakeUsers);
        } else {
            headers.add("statusOperation", "not populated");
            return ResponseEntity.accepted().body(null);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getAllUsers");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        return ResponseEntity.accepted().headers(headers).body(userService.getAllUsers());

    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createBook (@RequestBody User user){
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "createUser");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        User userCreated = userService.createUser(user);

        if (userCreated != null) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(userCreated);
        } else {
            headers.add("statusOperation", "not created");
            return ResponseEntity.accepted().body(null);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser (@PathVariable String id, @RequestBody User dataUser) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateUser");
        headers.add("version", "api 1.0");

        Optional<User> userFound = userService.findUserById(id);

        if (userFound.isPresent()){
            userService.updateUser(userFound.get(), dataUser);
            headers.add("operationStatus","updated");
            return  ResponseEntity.accepted().headers(headers).body(userFound.get());
        } else {
            headers.add("operationStatus","not found");
            return ResponseEntity.accepted().headers(headers).body(null);}

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
