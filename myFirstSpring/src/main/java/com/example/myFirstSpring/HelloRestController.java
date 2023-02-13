package com.example.myFirstSpring;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/hellorest")
    public String helloWorld(){

        return "Hello Worlds and class!!!!";
    }


}
