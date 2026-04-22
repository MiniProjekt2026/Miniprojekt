package com.example.wish.repository;

import com.example.wish.model.Wish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {

    private final WishRepository wishRepository;

    public WishRowMapper(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        int wishId = rs.getInt("wish_id");
        return new Wish(
                wishId,
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                wishRepository.loadTagsForWish(wishId),
                rs.getString("product_link"),
                rs.getBoolean("reserved"),
                rs.getInt("wish_list_id")
        );
    }
}
