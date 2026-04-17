package com.example.wish.repository;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishListRowMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WishList(
                rs.getInt("wish_list_id"),
                rs.getInt("user_id"),
                rs.getString("name")
        );
    }
}

//    private int wishListId;
//    private int userId;
//    private String name;