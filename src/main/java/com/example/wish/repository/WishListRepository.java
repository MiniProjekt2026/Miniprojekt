package com.example.wish.repository;

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

    public List<WishList> getAllWishes() {
        String sql = """
                SELECT wl.wish_id, wl.name, wl.description, wl.price, wl.quantity, wl.product_link
                FROM wish_list wl
                """;

        List<WishList> wishList = jdbcTemplate.query(sql, new WishListRowMapper());

        for (WishList wishlist : wishList) {
            wishlist.setTags(getTagsByWishName(wishlist.getName()));
        }

        return wishList;

    }

    public void addWish(WishList wishList) {
        String sqlWish = "INSERT INTO wish_list(name, description, price, quantity, product_link, user_id) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sqlWish,
                wishList.getName(),
                wishList.getDescription(),
                wishList.getPrice(),
                wishList.getQuantity(),
                wishList.getProductLink(),
                wishList.getUserId()
        );

        Integer wishId = findWishIdByName(wishList.getName());

        if (wishId == null) {
            throw new IllegalArgumentException("Ønsket blev oprettet, men wish_id kunne ikke findes");
        }

        for (String tag : wishList.getTags()) {
            Integer tagId = findTagIdByDescription(tag);

            if (tagId != null) {
                jdbcTemplate.update("INSERT INTO wish_list_tags (tag_id, wish_id) VALUES (?, ?)",
                        tagId,
                        wishId
                );
            }
        }

    }

    public List<String> getTags() {
        String getTags = "SELECT tag_name FROM tag";

        return jdbcTemplate.query(getTags,
                (rs, rowNum) -> rs.getString("tag_name"));
    }

    public List<String> getTagsByWishName(String name) {
        String sql = """
                SELECT t.tag_name
                FROM tags t
                JOIN wish_list_tag tat ON t.tag_id = tat.tag_id
                JOIN wish_list ta ON tat.wish_id = ta.wish_id
                WHERE ta.name = ?
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getString("tag_name"),
                name
        );
    }

    public Integer findTagIdByDescription(String TagName) {
        List<Integer> result = jdbcTemplate.query(
                "SELECT tag_id FROM tags WHERE tag_description = ?",
                (rs, rowNum) -> rs.getInt("tag_id"),
                TagName
        );

        return result.isEmpty() ? null : result.get(0);

    }

    public boolean updateWish(String name, WishList updatedWishList) {
        Integer wishId = findWishIdByName(name);

        if (wishId==null){
            throw new IllegalArgumentException("Der kunne ikke findes et ønske med det id");
        }

        String updateSql = """
                UPDATE wish_list
                SET NAME = ?, DESCRIPTION = ?, PRICE = ?, QUANTITY = ?, PRODUCT_LINK = ?
                WHERE wish_id=?
                """;

        int rows = jdbcTemplate.update(updateSql,
                updatedWishList.getName(),
                updatedWishList.getDescription(),
                updatedWishList.getPrice(),
                updatedWishList.getQuantity(),
                updatedWishList.getProductLink());

        if (rows==0){
            return false;
        }

        String insertTagSql = "INSERT INTO wish_list_tag (tag_id, wish_id) VALUES (?,?)";
        for (String tag : updatedWishList.getTags()){
            Integer tagId = findTagIdByDescription(tag);
            if(tagId != null){
                jdbcTemplate.update(insertTagSql, tagId, wishId);
            }
        }
        return true;
    }

    private Integer findWishIdByName(String wishName) {
        List<Integer> result = jdbcTemplate.query(
                "SELECT wish_id FROM wish_list WHERE name = ?",
                (rs, rowNum) -> rs.getInt("wish_id"), wishName
        );

        return result.isEmpty() ? null : result.get(0);

    }

    private List<WishList> getWishesByUserId(int userId) {
        String sql = """
                SELECT wish_id, name, description, price, quantity, product_link, user_id
                FROM wish_list
                WHERE user_id = ?
                """;

        List<WishList> wishList = jdbcTemplate.query(sql, new WishListRowMapper(), userId);

        for (WishList wish : wishList) {
            wish.setTags(getTagsByWishId(wish.getId()));
        }
        return wishList;
    }


    private List<String> getTagsByWishId(int id) {
        String sql = """
                  SELECT t.tag_name
                            FROM tag t
                            JOIN wish_list_tag wlt ON t.tag_id = wlt.tag_id
                            WHERE wlt.wish_id = ?
                """;

        return jdbcTemplate.query(
                sql, (rs, rowNum) -> rs.getString("tag_name"), id);


    }

    public boolean deleteWish(String name) {
        Integer wishId = findWishIdByName(name);

        if (wishId == null) {
            return false;
        }

        String deleteTagsSQL = """
                DELETE FROM wish_list_tag
                    WHERE wish_id = ?
                """;
        jdbcTemplate.update(deleteTagsSQL, wishId);

        String deleteWishListSQL = """
                DELETE FROM wish_list
                    WHERE wish_id = ?
                """;

        int rows = jdbcTemplate.update(deleteWishListSQL, wishId);

        return rows > 0;
    }

}
