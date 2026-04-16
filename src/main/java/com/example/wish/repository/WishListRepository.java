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

    public WishList findWishListById(int wishListId) {
        String sql = "SELECT * FROM wish_list WHERE wish_list_id = ?";

        List<WishList> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
            WishList wishList = new WishList();
            wishList.setWishListId(rs.getInt("wish_list_id"));
            wishList.setUserId(rs.getInt("user_id"));
            wishList.setName(rs.getString("name"));
            return wishList;
        }, wishListId);

        return result.isEmpty() ? null : result.get(0);
    }

    public boolean updateWishList(int wishListId, WishList wishList) {
        String sql = "UPDATE wish_list SET name = ? WHERE wish_list_id = ?";

        return jdbcTemplate.update(sql,
                wishList.getName(),
                wishListId
        ) > 0;
    }
}
