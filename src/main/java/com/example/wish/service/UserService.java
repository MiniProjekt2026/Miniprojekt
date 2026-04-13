package com.example.wish.service;

import com.example.wish.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void createUser(){

    }

    public void verifyUsername(){

    }

    //public void updatePassword
}
