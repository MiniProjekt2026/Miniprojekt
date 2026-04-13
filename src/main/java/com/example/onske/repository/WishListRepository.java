package com.example.onske.repository;


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

    public void addWishes(){

    }

    public List<String> getTags(){

    }

    public List<String> getTagsByWishName(){

    }

    public void getTagIdByDescription(){

    }

    public void updateWish(){

    }

    public void getWishIdByName(){

    }

    public void deleteWish(){

    }

}
