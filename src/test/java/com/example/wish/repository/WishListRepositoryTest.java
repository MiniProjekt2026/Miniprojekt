package com.example.wish.repository;

import com.example.wish.model.WishList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class WishListRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private WishListRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new WishListRepository(jdbcTemplate);
    }

    @Test
    void findWishListById_returnsNull_whenNotFound() {
        when(jdbcTemplate.query(anyString(), any(WishListRowMapper.class), anyInt()))
                .thenReturn(List.of());

        WishList result = repository.findWishListById(1);

        assertNull(result);
    }

    @Test
    void updateWishList_returnsTrue_whenUpdated() {
        when(jdbcTemplate.update(anyString(), anyString(), anyInt()))
                .thenReturn(1);

        WishList wl = new WishList();
        wl.setName("Updated");

        boolean result = repository.updateWishList(1, wl);

        assertTrue(result);
    }

    @Test
    void deleteWishList_returnsFalse_whenNothingDeleted() {
        when(jdbcTemplate.update(anyString(), anyInt()))
                .thenReturn(0);

        boolean result = repository.deleteWishList(1);

        assertFalse(result);
    }

    @Test
    void addWishList_executesInsert() {
        WishList wl = new WishList();
        wl.setName("Test");
        wl.setUserId(1);

        repository.addWishList(wl);

        verify(jdbcTemplate, times(1))
                .update(anyString(), eq("Test"), eq(1));
    }
}