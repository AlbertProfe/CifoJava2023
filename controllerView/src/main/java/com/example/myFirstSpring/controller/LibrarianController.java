package com.example.myFirstSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    @RequestMapping("/librarians")
    public String getAllLibrarians(Model model){

        model.addAttribute("todayDate", new Date().toString());
        model.addAttribute("librarians", "");

        return "librarian/librarians";
    }




    @RequestMapping("/createFakeLibrarians")
    public String createFakeLibrarians(@RequestParam int qty ){
        //RequestParam("qty") int qty
        return "redirect:home";
    }



}

