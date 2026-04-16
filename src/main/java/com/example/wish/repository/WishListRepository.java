package com.example.wish.repository;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishList> getAllWishLists() {
        String sql = """
                SELECT wl.wish_list_id, wl.name
                FROM wish wl
                """;

        List<WishList> wishList = jdbcTemplate.query(sql, new WishListRowMapper());

        return wishList;
    }

    public WishList findWishListByName(String name) {
        String sql = "SELECT * FROM wish_list WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishListRowMapper(), name);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateWishList(String oldName, WishList updatedWishList) {
        Integer wishListId = findWishListIdByName(oldName);

        if (wishListId == null) {
            throw new IllegalArgumentException("Der kunne ikke findes en ønskeliste med det navn");
        }

        String sql = """
            UPDATE wish_list
            SET name = ?
            WHERE wish_list_id = ?
            """;

        int rows = jdbcTemplate.update(sql,
                updatedWishList.getName(),
                wishListId);

        return rows > 0;
    }

    public Integer findWishListIdByName(String name) {
        String sql = "SELECT wish_list_id FROM wish_list WHERE name = ?";

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, name);
        } catch (Exception e) {
            return null;
        }
    }

    //:)
}
