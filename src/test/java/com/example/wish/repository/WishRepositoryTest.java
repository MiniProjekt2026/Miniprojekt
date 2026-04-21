package com.example.wish.repository;

import com.example.wish.model.Wish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private WishRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new WishRepository(jdbcTemplate);
    }

    @Test
    void deleteWishById_returnsTrue_whenDeleted() {
        when(jdbcTemplate.update(anyString(), anyInt()))
                .thenReturn(1);

        boolean result = repository.deleteWishById(1);

        assertTrue(result);
    }

    @Test
    void deleteWishById_returnsFalse_whenNotDeleted() {
        when(jdbcTemplate.update(anyString(), anyInt()))
                .thenReturn(0);

        boolean result = repository.deleteWishById(1);

        assertFalse(result);
    }

    @Test
    void getTags_returnsList() {
        when(jdbcTemplate.query(anyString(), any(org.springframework.jdbc.core.RowMapper.class)))
                .thenReturn(List.of("Tech", "Gaming"));

        List<String> tags = repository.getTags();

        assertEquals(2, tags.size());
        assertTrue(tags.contains("Tech"));
    }

    @Test
    void addWish_executesInsert() {
        Wish wish = new Wish();
        wish.setName("Laptop");
        wish.setDescription("Gaming");
        wish.setPrice(1000);
        wish.setQuantity(1);
        wish.setProductLink("link");
        wish.setWishListId(1);
        wish.setTags(null); // avoid tag logic here

        repository.addWish(wish);

        verify(jdbcTemplate).update(
                anyString(),
                eq("Laptop"),
                eq("Gaming"),
                eq(1000.0),
                eq(1),
                eq("link"),
                eq(false),
                eq(1)
        );
    }

    @Test
    void updateWish_returnsTrue_andDeletesOldTags() {
        Wish wish = new Wish();
        wish.setName("Updated");
        wish.setDescription("Desc");
        wish.setPrice(100);
        wish.setQuantity(2);
        wish.setProductLink("link");
        wish.setTags(List.of("NewTag"));

        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyDouble(), anyInt(), anyString(), anyInt()))
                .thenReturn(1);

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString()))
                .thenReturn(1);

        boolean result = repository.updateWish(1, wish);

        assertTrue(result);

        verify(jdbcTemplate).update("DELETE FROM wish_tag WHERE wish_id = ?", 1);
    }

    @Test
    void findWishById_returnsNull_whenNotFound() {
        when(jdbcTemplate.query(anyString(), any(WishRowMapper.class), anyInt()))
                .thenReturn(List.of());

        Wish result = repository.findWishById(1);

        assertNull(result);
    }
}