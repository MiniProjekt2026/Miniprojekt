package com.example.wish.controller;

import com.example.wish.model.User;
import com.example.wish.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void createUser_returnsView() throws Exception {

        mockMvc.perform(get("/user/createUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("createUser"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void saveUser_redirects_whenSuccess() throws Exception {

        mockMvc.perform(post("/user/saveUser")
                        .flashAttr("user", new User()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login"));
    }

    @Test
    void saveUser_returnsCreateView_whenException() throws Exception {

        doThrow(new IllegalArgumentException("User exists"))
                .when(userService)
                .createUser(any(User.class));

        mockMvc.perform(post("/user/saveUser")
                        .flashAttr("user", new User()))
                .andExpect(status().isOk())
                .andExpect(view().name("createUser"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void showLoginPage_returnsView() throws Exception {

        mockMvc.perform(get("/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void loginUser_redirects_whenSuccess() throws Exception {

        User user = new User();
        user.setUserId(1);

        when(userService.findByUsername("test"))
                .thenReturn(user);

        when(userService.login("test", "1234"))
                .thenReturn(true);

        mockMvc.perform(post("/user/login")
                        .param("username", "test")
                        .param("password", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home/1"));
    }

    @Test
    void loginUser_returnsLogin_whenFailed() throws Exception {

        when(userService.findByUsername("wrong"))
                .thenReturn(null);

        when(userService.login("wrong", "bad"))
                .thenReturn(false);

        mockMvc.perform(post("/user/login")
                        .param("username", "wrong")
                        .param("password", "bad"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}