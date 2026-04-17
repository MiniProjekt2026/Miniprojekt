package com.example.wish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String publicFrontpage() {
        return "forside";
    }

    @GetMapping("/home/{userId}")
    public String userFrontpage(@PathVariable int userId, Model model) {
        model.addAttribute("userId", userId);
        return "home";
    }
}