package com.example.wish.repository;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;
    private final WishListService wishListService;

    public WishListRepository(JdbcTemplate jdbcTemplate, WishListService wishListService) {
        this.jdbcTemplate = jdbcTemplate;
        this.wishListService = wishListService;
    }

    public List<WishList> getAllWishLists() {
        String sql = """
                SELECT wl.wish_list_id, wl.name
                FROM wish wl
                """;

        List<WishList> wishList = jdbcTemplate.query(sql, new WishListRowMapper());

        return wishList;
    }

    public WishList findWishListByUserId(int userId) {
        String sql = """
                SELECT * FROM wish_list WHERE userId = ?
                """;

        List<WishList> result = jdbcTemplate.query(sql,(rs, rowNum) -> {
            WishList wishList = new WishList();
            wishList.setWishListId(rs.getInt("wish_list_id"));
            wishList.setUserId(rs.getInt("user_id"));
            wishList.setName(rs.getString("name"));
            return wishList;
        }, userId);

        return result.isEmpty() ? null : result.get(0);
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

    public WishList findWishListByIdAndUserId(int userId, int wishListId) {
        String sql = """
            SELECT * 
            FROM wish_list 
            WHERE user_id = ? AND wish_list_id = ?
            """;

        List<WishList> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
            WishList wishList = new WishList();
            wishList.setWishListId(rs.getInt("wish_list_id"));
            wishList.setUserId(rs.getInt("user_id"));
            wishList.setName(rs.getString("name"));
            return wishList;
        }, userId, wishListId);

        return result.isEmpty() ? null : result.get(0);
    }

    public boolean updateWishList(int wishListId, WishList wishList) {
        String sql = "UPDATE wish_list SET name = ? WHERE wish_list_id = ?";

        return jdbcTemplate.update(sql,
                wishList.getName(),
                wishListId
        ) > 0;
    }

    public void addWishList(WishList wishList) {
        String sqlWishList = "INSERT INTO wish_list(name, user_id) VALUES (?,?,?)";
        jdbcTemplate.update(sqlWishList,
                wishList.getName(),
                wishList.getUserId()
        );

        Integer wishId = findWishListIdByName(wishList.getName());

        if (wishId == null) {
            throw new IllegalArgumentException("Ønsket blev oprettet, men wish_id kunne ikke findes");
        }
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
