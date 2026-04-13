package com.example.wish.repository;

import com.example.wish.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<WishList> getAllWishes(){
        String sql = """
                SELECT wl.wish_id, wl.name, wl.description, wl.price, wl.quantity, wl.product_link
                FROM wish_list wl
                """;

        List<WishList> wishList = jdbcTemplate.query(sql, new WishListRowMapper());

        for (WishList wishlist : wishList){
            wishlist.setTags(getTagsByWishName(wishlist.getName()));
        }

        return wishList;

    }

    public void addWish(WishList wishList) {
        String sqlWish ="INSERT INTO wish_list(name, description, price, quantity, product_link) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sqlWish,
                wishList.getName(),
                wishList.getDescription(),
                wishList.getPrice(),
                wishList.getQuantity(),
                wishList.getProductLink()
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
        String getTags = "SELECT tag_name FROM tags";

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

    public void updateWish(){

    }

    private Integer findWishIdByName(String wishName) {
        List<Integer> result = jdbcTemplate.query(
                "SELECT wish_id FROM wish_list WHERE name = ?",
                (rs, rowNum) -> rs.getInt("wish_id"), wishName
        );

        return result.isEmpty() ? null : result.get(0);

    }

    public void deleteWish(){

    }

}
