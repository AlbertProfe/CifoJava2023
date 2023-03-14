package com.example.myFirstSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String gethome(Model model){

        model.addAttribute("todayDate", new Date().toString());
        return "home";
    }

}
