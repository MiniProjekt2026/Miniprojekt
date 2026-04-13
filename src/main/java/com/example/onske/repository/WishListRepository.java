package com.example.onske.repository;


import com.example.onske.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public void getAllWishes(){

    }

    public void addWish(WishList wishList) {
        String sqlWish ="INSERT INTO wish_list (name, description, price, quantity, product_link) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sqlWish,
                wishList.getName(),
                wishList.getDescription(),
                wishList.getPrice(),
                wishList.getQuantity(),
                wishList.getProductLink()
        );

    }

    public List<String> getTags(){

    }

    public List<String> getTagsByWishName(){

    }

    public void getTagIdByDescription(){

    }

    public void updateWish(){

    }

    private Integer getWishIdByName(String wishListName) {
        List<Integer> result = jdbcTemplate.query(
            "SELECT wish_list_id
        )

    }

    public void deleteWish(){

    }

}
