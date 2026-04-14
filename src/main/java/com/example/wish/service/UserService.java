package com.example.wish.service;

import com.example.wish.model.User;
import com.example.wish.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        if(userRepository.verifyUsername(user.getUsername())){
            throw new IllegalArgumentException("Username fines allerede");
        }

        userRepository.createUser(user);
    }

    public boolean verifyUsername(String username) {
        return userRepository.verifyUsername(username);
    }

    public boolean login(String username, String password) {
        return userRepository.verifyUser(username, password);
    }

    //public void updatePassword
}
