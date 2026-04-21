package com.example.wish.repository;

import com.example.wish.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password="
})
@ActiveProfiles("test")
class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository repository;

    @Test
    void createUser_and_verifyUser() {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("1234");

        repository.createUser(user);

        boolean exists = repository.verifyUser("newuser", "1234");

        assertTrue(exists);
    }

    @Test
    void verifyUsername_returnsTrue_whenExists() {
        boolean exists = repository.verifyUsername("testuser");

        assertTrue(exists);
    }

    @Test
    void findByUsername_returnsUser() {
        User user = repository.findByUsername("testuser");

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void findByUsername_returnsNull_whenNotFound() {
        User user = repository.findByUsername("doesnotexist");

        assertNull(user);
    }
}