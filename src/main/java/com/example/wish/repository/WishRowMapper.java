package com.example.wish.repository;

import com.example.wish.model.Wish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class WishRowMapper implements RowMapper<Wish> {

    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Wish(
                rs.getInt("wish_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                new ArrayList<>(),
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