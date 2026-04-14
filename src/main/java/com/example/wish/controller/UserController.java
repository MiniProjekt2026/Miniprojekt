package com.example.wish.controller;

import com.example.wish.model.User;
import com.example.wish.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createUser")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/user/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            return "createUser";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {

        boolean isValidUser = userService.login(username, password);

        if (isValidUser) {
            return "redirect:/wishlist/" + username;
        } else {
            model.addAttribute("error", "Forkert username eller password");
            return "login";
        }
    }
}
