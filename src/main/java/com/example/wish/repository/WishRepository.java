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
            SELECT w.wish_id, w.wish_list_id, w.name, w.description, w.price, w.quantity, w.product_link
            FROM wish w
            """;

        List<Wish> wishes = jdbcTemplate.query(sql, new WishRowMapper());

        for (Wish w : wishes) {
            w.setTags(getTagsByWishId(w.getId()));
        }

        return wishes;
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

    public boolean updateWish(int wishId, Wish wish) {
        String sql = """
        UPDATE wish
        SET name = ?, description = ?, price = ?, quantity = ?, product_link = ?
        WHERE wish_id = ?
    """;

        return jdbcTemplate.update(sql,
                wish.getName(),
                wish.getDescription(),
                wish.getPrice(),
                wish.getQuantity(),
                wish.getProductLink(),
                wishId
        ) > 0;
    }

    public Wish findWishById(int wishId) {
        String sql = "SELECT * FROM wish WHERE wish_id = ?";

        List<Wish> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Wish wish = new Wish();
            wish.setId(rs.getInt("wish_id"));
            wish.setWishListId(rs.getInt("wish_list_id"));
            wish.setName(rs.getString("name"));
            wish.setDescription(rs.getString("description"));
            wish.setPrice(rs.getDouble("price"));
            wish.setQuantity(rs.getInt("quantity"));
            wish.setProductLink(rs.getString("product_link"));
            return wish;
        }, wishId);

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

    public boolean deleteWishesByWishListId(int wishListId) {
        String sql = "DELETE FROM wish WHERE wish_list_id = ?";
        return jdbcTemplate.update(sql, wishListId) >= 0;
    }

    public boolean deleteWishById(int wishId) {
        String sql = "DELETE FROM wish WHERE wish_id = ?";
        return jdbcTemplate.update(sql, wishId) > 0;
    }

    public Integer findWishIdByName(String name) {
        String sql = "SELECT wish_id FROM wish WHERE name = ? ORDER BY wish_id DESC LIMIT 1";

        List<Integer> result = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getInt("wish_id"),
                name
        );

        return result.isEmpty() ? null : result.get(0);
    }

    public List<Wish> findWishesByWishListId(int wishListId){
        String sql = """
            SELECT wish_id, name, description, price, quantity, product_link, wish_list_id
            FROM wish
            WHERE wish_list_id = ?
            """;

        List<Wish> wishes = jdbcTemplate.query(sql, new WishRowMapper(), wishListId);

        for (Wish w : wishes) {
            w.setTags(getTagsByWishId(w.getId()));
        }

        return wishes;
    }

}
