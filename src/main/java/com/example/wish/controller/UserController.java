package com.example.wish.controller;

import com.example.wish.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService){
        this.userService=userService;
    }

    @GetMapping("/createUser")
    public void createUser(){

    }

    @PostMapping("/saveUser")
    public void saveUser(){

    }

    @PostMapping ("/login")
    public void loginUser(){

    }
}
