package com.example.wish.repository;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishList> getAllWishLists() {
        String sql = """
                SELECT wl.wish_list_id, user_id, name
                FROM wish_list 
                """;

        List<WishList> wishList = jdbcTemplate.query(sql, new WishListRowMapper());

        return wishList;
    }

    public List<WishList> findWishListsByUserId(int userId) {
        String sql = """
                SELECT wish_list_id, user_id, name
                FROM wish_list
                WHERE user_id = ?
                """;

        return jdbcTemplate.query(sql, new WishListRowMapper(), userId);
    }

    public WishList findWishListById(int wishListId) {
        String sql = """
                SELECT wish_list_id, user_id, name
                FROM wish_list
                WHERE wish_list_id = ?
                """;

        List<WishList> result = jdbcTemplate.query(sql, new WishListRowMapper(), wishListId);

        return result.isEmpty() ? null : result.get(0);
    }

    public WishList findWishListByIdAndUserId(int userId, int wishListId) {
        String sql = """
                SELECT wish_list_id, user_id, name
                FROM wish_list
                WHERE user_id = ? AND wish_list_id = ?
                """;

        List<WishList> result = jdbcTemplate.query(sql, new WishListRowMapper(), userId, wishListId);

        return result.isEmpty() ? null : result.get(0);
    }

    public boolean updateWishList(int wishListId, WishList wishList) {
        String sql = "UPDATE wish_list SET name = ? WHERE wish_list_id = ?";

        return jdbcTemplate.update(sql, wishList.getName(), wishListId) > 0;
    }

    public boolean deleteWishList(int wishListId) {
        String sql = "DELETE FROM wish_list WHERE wish_list_id = ?";
        return jdbcTemplate.update(sql, wishListId) > 0;
    }
}
