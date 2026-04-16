package com.example.wish.repository;

import com.example.wish.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> getAllWishes() {
        String sql = """
                SELECT w.wish_id, w.name, w.description, w.price, w.quantity, w.product_link
                FROM wish w
                """;

        List<Wish> wish = jdbcTemplate.query(sql, new WishRowMapper());

        for (Wish w : wish) {
            w.setTags(getTagsByWishName(w.getName()));
        }

        return wish;

    }

    public void addWish(Wish wish) {
        String sqlWish = "INSERT INTO wish(name, description, price, quantity, product_link, wish_list_id) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sqlWish,
                wish.getName(),
                wish.getDescription(),
                wish.getPrice(),
                wish.getQuantity(),
                wish.getProductLink(),
                wish.getWishListId()
        );

        Integer wishId = findWishIdByName(wish.getName());

        if (wishId == null) {
            throw new IllegalArgumentException("Ønsket blev oprettet, men wish_id kunne ikke findes");
        }

        for (String tag : wish.getTags()) {
            Integer tagId = findTagIdByDescription(tag);

            if (tagId != null) {
                jdbcTemplate.update("INSERT INTO wish_tags (tag_id, wish_id) VALUES (?, ?)",
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
                FROM tag t
                JOIN wish_tag tat ON t.tag_id = tat.tag_id
                JOIN wish ta ON tat.wish_id = ta.wish_id
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
                "SELECT tag_id FROM tag WHERE tag_description = ?",
                (rs, rowNum) -> rs.getInt("tag_id"),
                TagName
        );

        return result.isEmpty() ? null : result.get(0);

    }

    public boolean updateWish(String name, Wish updatedWish) {
        Integer wishId = findWishIdByName(name);

        if (wishId==null){
            throw new IllegalArgumentException("Der kunne ikke findes et ønske med det id");
        }

        String updateSql = """
                UPDATE wish
                SET NAME = ?, DESCRIPTION = ?, PRICE = ?, QUANTITY = ?, PRODUCT_LINK = ?
                WHERE wish_id=?
                """;

        int rows = jdbcTemplate.update(updateSql,
                updatedWish.getName(),
                updatedWish.getDescription(),
                updatedWish.getPrice(),
                updatedWish.getQuantity(),
                updatedWish.getProductLink());

        if (rows==0){
            return false;
        }

        String insertTagSql = "INSERT INTO wish_tag (tag_id, wish_id) VALUES (?,?)";
        for (String tag : updatedWish.getTags()){
            Integer tagId = findTagIdByDescription(tag);
            if(tagId != null){
                jdbcTemplate.update(insertTagSql, tagId, wishId);
            }
        }
        return true;
    }

    private Integer findWishIdByName(String wishName) {
        List<Integer> result = jdbcTemplate.query(
                "SELECT wish_id FROM wish WHERE name = ?",
                (rs, rowNum) -> rs.getInt("wish_id"), wishName
        );

        return result.isEmpty() ? null : result.get(0);

    }

    private List<Wish> getWishesByUserId(int userId) {
        String sql = """
                SELECT wish_id, name, description, price, quantity, product_link, user_id
                FROM wish
                WHERE user_id = ?
                """;

        List<Wish> wish = jdbcTemplate.query(sql, new WishRowMapper(), userId);

        for (Wish w : wish) {
            w.setTags(getTagsByWishId(w.getId()));
        }
        return wish;
    }


    private List<String> getTagsByWishId(int id) {
        String sql = """
                  SELECT t.tag_name
                            FROM tag t
                            JOIN wish_tag wlt ON t.tag_id = wlt.tag_id
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
                DELETE FROM wish_tag
                    WHERE wish_id = ?
                """;
        jdbcTemplate.update(deleteTagsSQL, wishId);

        String deleteWishSQL = """
                DELETE FROM wish
                    WHERE wish_id = ?
                """;

        int rows = jdbcTemplate.update(deleteWishSQL, wishId);

        return rows > 0;
    }

}
