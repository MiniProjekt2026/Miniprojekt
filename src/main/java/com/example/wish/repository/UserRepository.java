package com.example.wish.repository;


import com.example.wish.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }

    public boolean verifyUser(String username, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        return count != null && count > 0;
    }

    public boolean verifyUsername(String username) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id")); // tjek kolonnenavn!
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        }, username);
    }

    //public void editPassword()
}
