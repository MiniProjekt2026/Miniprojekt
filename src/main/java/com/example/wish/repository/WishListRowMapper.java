package com.example.wish.repository;

import com.example.wish.model.WishList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class WishListRowMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WishList(
                rs.getInt("wish_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                new ArrayList<>(),
                rs.getString("product_link"),
                rs.getInt("user_id")
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