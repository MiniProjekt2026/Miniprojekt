package com.example.wish.repository;

import com.example.wish.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new UserRepository(jdbcTemplate);
    }

    @Test
    void createUser_executesInsert() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("1234");

        repository.createUser(user);

        verify(jdbcTemplate).update(
                anyString(),
                eq("test"),
                eq("1234")
        );
    }

    @Test
    void verifyUser_returnsTrue_whenMatchExists() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString(), anyString()))
                .thenReturn(1);

        boolean result = repository.verifyUser("test", "1234");

        assertTrue(result);
    }

    @Test
    void verifyUser_returnsFalse_whenNoMatch() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString(), anyString()))
                .thenReturn(0);

        boolean result = repository.verifyUser("test", "wrong");

        assertFalse(result);
    }

    @Test
    void verifyUsername_returnsTrue_whenExists() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString()))
                .thenReturn(1);

        boolean result = repository.verifyUsername("test");

        assertTrue(result);
    }

    @Test
    void verifyUsername_returnsFalse_whenNotExists() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString()))
                .thenReturn(0);

        boolean result = repository.verifyUsername("nope");

        assertFalse(result);
    }

    @Test
    void findByUsername_returnsUser_whenFound() {

        User user = new User();
        user.setUserId(1);
        user.setUsername("test");
        user.setPassword("1234");

        when(jdbcTemplate.query(
                anyString(),
                any(ResultSetExtractor.class),
                any(Object[].class)
        )).thenReturn(user);

        User result = repository.findByUsername("test");

        assertNotNull(result);
        assertEquals("test", result.getUsername());
    }

    @Test
    void findByUsername_returnsNull_whenNotFound() {

        when(jdbcTemplate.query(
                anyString(),
                any(ResultSetExtractor.class),
                any(Object[].class)
        )).thenReturn(null);

        User result = repository.findByUsername("nope");

        assertNull(result);
    }
}