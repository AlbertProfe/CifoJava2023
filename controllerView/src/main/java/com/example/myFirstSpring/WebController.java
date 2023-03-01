package com.example.myFirstSpring;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class WebController {

    @RequestMapping("/home")
    public String getHome(Model model){

        //model.addAttribute("userName", "John Connor");
        //model.addAttribute("userAge", 56);
        model.addAttribute("todayDate", new Date().toString());
        model.addAttribute("users", createFakeUsers(10));
        //model.addAttribute("borrows", borrowService.getBorrows());

        return "homeweb.html";
    }


    @RequestMapping("/createFakeUsers")
    public String createFakeUsers () {

        return "redirect:home";
    }




    public static List<User> createFakeUsers(int number) {
        // create faker object to use as
        // builder for user
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        User newUser;

        for (int i = 0; i < number; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1001, 9999 + 1);
            newUser =new User();

            int userId = randomNum;
            newUser.setUserId(userId);

            String userName = faker.name().firstName();
            newUser.setName(userName);

            String userAddress = faker.address().fullAddress();
            newUser.setAddress(userAddress);

            int userAge = faker.number().numberBetween(0, 100);
            newUser.setAge(userAge);

            users.add(newUser);

        }
        //System.out.println(users.size());
        return  users;
    }
}