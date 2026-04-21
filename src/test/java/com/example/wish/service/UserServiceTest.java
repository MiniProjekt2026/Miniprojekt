package com.example.wish.service;

import com.example.wish.model.User;
import com.example.wish.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_throwsException_whenUsernameExists() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("1234");

        when(userRepository.verifyUsername("test")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));

        verify(userRepository, never()).createUser(any());
    }

    @Test
    void createUser_callsRepository_whenUsernameNotExists() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("1234");

        when(userRepository.verifyUsername("test")).thenReturn(false);

        userService.createUser(user);

        verify(userRepository).createUser(user);
    }

    @Test
    void login_returnsTrue_whenCredentialsValid() {
        when(userRepository.verifyUser("test", "1234")).thenReturn(true);

        boolean result = userService.login("test", "1234");

        assertTrue(result);
    }

    @Test
    void login_returnsFalse_whenCredentialsInvalid() {
        when(userRepository.verifyUser("test", "wrong")).thenReturn(false);

        boolean result = userService.login("test", "wrong");

        assertFalse(result);
    }

    @Test
    void verifyUsername_delegatesToRepository() {
        when(userRepository.verifyUsername("test")).thenReturn(true);

        boolean result = userService.verifyUsername("test");

        assertTrue(result);
        verify(userRepository).verifyUsername("test");
    }

    @Test
    void findByUsername_returnsUser() {
        User user = new User();
        user.setUsername("test");

        when(userRepository.findByUsername("test")).thenReturn(user);

        User result = userService.findByUsername("test");

        assertNotNull(result);
        assertEquals("test", result.getUsername());
    }
}