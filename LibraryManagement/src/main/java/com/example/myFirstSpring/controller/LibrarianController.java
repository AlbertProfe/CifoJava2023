package com.example.myFirstSpring.controller;

import com.example.myFirstSpring.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    @Autowired
    LibrarianService librarianService;

    @RequestMapping("/librarians")
    public String getAllLibrarians(Model model){

        model.addAttribute("todayDate", new Date().toString());
        model.addAttribute("librarians", librarianService.getAllLibrarians());

        return "librarian/librarians";
    }




    @RequestMapping("/createFakeLibrarians")
    public String createFakeLibrarians(@RequestParam int qty ){
        //RequestParam("qty") int qty
        return "redirect:home";
    }



}

