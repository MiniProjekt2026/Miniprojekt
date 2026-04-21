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

    // ── helpers ──────────────────────────────────────────────────────────────

    public List<String> loadTagsForWish(int wishId) {
        String sql = """
                SELECT t.tag_name
                FROM tag t
                JOIN wish_tag wt ON t.tag_id = wt.tag_id
                WHERE wt.wish_id = ?
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("tag_name"), wishId);
    }

    public List<Wish> getAllWishes() {
        String sql = """
                SELECT wish_id, name, description, price, quantity, product_link, reserved, wish_list_id
                FROM wish
                """;
        // FIX: pass 'this' so WishRowMapper can call loadTagsForWish
        return jdbcTemplate.query(sql, new WishRowMapper(this));
    }

    public List<Wish> findWishesByWishListId(int wishListId) {
        String sql = """
                SELECT wish_id, name, description, price, quantity, product_link, reserved, wish_list_id
                FROM wish
                WHERE wish_list_id = ?
                """;
        // FIX: pass 'this'
        return jdbcTemplate.query(sql, new WishRowMapper(this), wishListId);
    }

    public Wish findWishById(int wishId) {
        String sql = """
                SELECT wish_id, name, description, price, quantity, product_link, reserved, wish_list_id
                FROM wish
                WHERE wish_id = ?
                """;
        // FIX: pass 'this'
        List<Wish> result = jdbcTemplate.query(sql, new WishRowMapper(this), wishId);
        return result.isEmpty() ? null : result.get(0);
    }

    public void addWish(Wish wish) {
        String sql = """
                INSERT INTO wish(name, description, price, quantity, product_link, reserved, wish_list_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                wish.getName(),
                wish.getDescription(),
                wish.getPrice(),
                wish.getQuantity(),
                wish.getProductLink(),
                wish.getWishListId()
        );

        if (wish.getTags() != null && !wish.getTags().isEmpty()) {
            Integer newWishId = jdbcTemplate.queryForObject(
                    "SELECT wish_id FROM wish WHERE wish_list_id = ? ORDER BY wish_id DESC LIMIT 1",
                    Integer.class,
                    wish.getWishListId()
            );
            if (newWishId != null) {
                saveTagsForWish(newWishId, wish.getTags());
            }
        }
    }

    public boolean updateWish(int wishId, Wish wish) {
        String sql = """
                UPDATE wish
                SET name = ?, description = ?, price = ?, quantity = ?, product_link = ?
                WHERE wish_id = ?
                """;
        boolean updated = jdbcTemplate.update(sql,
                wish.getName(),
                wish.getDescription(),
                wish.getPrice(),
                wish.getQuantity(),
                wish.getProductLink(),
                wishId
        ) > 0;

        if (updated) {
            jdbcTemplate.update("DELETE FROM wish_tag WHERE wish_id = ?", wishId);
            if (wish.getTags() != null && !wish.getTags().isEmpty()) {
                saveTagsForWish(wishId, wish.getTags());
            }
        }

        return updated;
    }

    public boolean deleteWishById(int wishId) {
        String sql = "DELETE FROM wish WHERE wish_id = ?";
        return jdbcTemplate.update(sql, wishId) > 0;
    }

    public List<String> getTags() {
        String sql = "SELECT tag_name FROM tag ORDER BY tag_name";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("tag_name"));
    }

    private void saveTagsForWish(int wishId, List<String> tagNames) {
        for (String tagName : tagNames) {
            jdbcTemplate.update(
                    "INSERT IGNORE INTO tag(tag_name) VALUES (?)",
                    tagName
            );
            Integer tagId = jdbcTemplate.queryForObject(
                    "SELECT tag_id FROM tag WHERE tag_name = ?",
                    Integer.class,
                    tagName
            );
            if (tagId != null) {
                jdbcTemplate.update(
                        "INSERT IGNORE INTO wish_tag(wish_id, tag_id) VALUES (?, ?)",
                        wishId, tagId
                );
            }
        }
    }

    public boolean reservedWishSetTrue(int wishId){
        String sql = "UPDATE wish SET reserved = true WHERE wish_id = ? AND reserved = FALSE";
        return jdbcTemplate.update(sql, wishId) > 0;
    }

    public boolean reservedWishSetFalse(int wishID){
        String sql = "UPDATE wish SET reserved = true WHERE wish_id = ? AND reserved = TRUE";
        return jdbcTemplate.update(sql, wishID)>0;
    }
}