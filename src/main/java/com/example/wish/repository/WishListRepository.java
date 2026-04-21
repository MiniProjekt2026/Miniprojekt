package com.example.wish.repository;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Wish> loadWishesForList(int wishListId) {
        String sql = """
                SELECT wish_id, name, description, price, quantity, product_link, reserved, wish_list_id
                FROM wish
                WHERE wish_list_id = ?
                """;

        List<Wish> wishes = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Wish(
                        rs.getInt("wish_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        loadTagsForWish(rs.getInt("wish_id")),
                        rs.getString("product_link"),
                        rs.getBoolean("reserved"),
                        rs.getInt("wish_list_id")
                ), wishListId);

        return wishes;
    }

    private List<String> loadTagsForWish(int wishId) {
        String sql = """
                SELECT t.tag_name
                FROM tag t
                JOIN wish_tag wt ON t.tag_id = wt.tag_id
                WHERE wt.wish_id = ?
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("tag_name"), wishId);
    }

    public List<WishList> getAllWishLists() {
        String sql = """
                SELECT wish_list_id, user_id, name
                FROM wish_list
                """;

        List<WishList> wishLists = jdbcTemplate.query(sql, new WishListRowMapper());
        for (WishList wl : wishLists) {
            wl.setWishes(loadWishesForList(wl.getWishListId()));
        }
        return wishLists;
    }

    public List<WishList> findWishListsByUserId(int userId) {
        String sql = """
                SELECT wish_list_id, user_id, name
                FROM wish_list
                WHERE user_id = ?
                """;

        List<WishList> wishLists = jdbcTemplate.query(sql, new WishListRowMapper(), userId);

        for (WishList wl : wishLists) {
            wl.setWishes(loadWishesForList(wl.getWishListId()));
        }
        return wishLists;
    }

    public WishList findWishListById(int wishListId) {
        String sql = """
                SELECT wish_list_id, user_id, name
                FROM wish_list
                WHERE wish_list_id = ?
                """;

        List<WishList> result = jdbcTemplate.query(sql, new WishListRowMapper(), wishListId);

        if (result.isEmpty()) return null;

        WishList wishList = result.get(0);

        wishList.setWishes(loadWishesForList(wishListId));
        return wishList;
    }

    public WishList findWishListByIdAndUserId(int userId, int wishListId) {
        String sql = """
                SELECT wish_list_id, user_id, name
                FROM wish_list
                WHERE user_id = ? AND wish_list_id = ?
                """;

        List<WishList> result = jdbcTemplate.query(sql, new WishListRowMapper(), userId, wishListId);

        if (result.isEmpty()) return null;

        WishList wishList = result.get(0);
        wishList.setWishes(loadWishesForList(wishListId));
        return wishList;
    }

    public boolean updateWishList(int wishListId, WishList wishList) {
        String sql = "UPDATE wish_list SET name = ? WHERE wish_list_id = ?";
        return jdbcTemplate.update(sql, wishList.getName(), wishListId) > 0;
    }

    public boolean deleteWishList(int wishListId) {
        String sql = "DELETE FROM wish_list WHERE wish_list_id = ?";
        return jdbcTemplate.update(sql, wishListId) > 0;
    }

    public void addWishList(WishList wishList) {
        String sql = "INSERT INTO wish_list(name, user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishList.getName(), wishList.getUserId());
    }

    public Integer findWishListIdByName(String name) {
        String sql = "SELECT wish_list_id FROM wish_list WHERE name = ? ORDER BY wish_list_id DESC LIMIT 1";
        List<Integer> result = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getInt("wish_list_id"),
                name
        );
        return result.isEmpty() ? null : result.get(0);
    }
}