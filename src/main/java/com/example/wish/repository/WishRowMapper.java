package com.example.wish.repository;

import com.example.wish.model.Wish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class WishRowMapper implements RowMapper<Wish> {

    private WishRepository wishRepository;

    @Override
    public Wish mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        int wishId = rs.getInt("wish_id");
        return new Wish(
                wishId,
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                wishRepository.loadTagsForWish(wishId),
                rs.getString("product_link"),
                rs.getInt("wish_list_id")
        );
    }
}


//  private int id;
//    private String name;
//    private String description;
//    private double price;
//    private int quantity;
//    private List<String> tags;
//    private String productLink;