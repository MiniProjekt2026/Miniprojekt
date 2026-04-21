package com.example.wish.service;

import com.example.wish.model.Wish;
import com.example.wish.repository.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishServiceTest {

    private WishRepository repository;
    private WishService service;

    @BeforeEach
    void setUp() {
        repository = mock(WishRepository.class);
        service = new WishService(repository);
    }

    @Test
    void updateWish_returnsUpdated_whenSuccess() {
        Wish existing = new Wish();
        existing.setWishId(1);
        existing.setName("Old");

        Wish newValues = new Wish();
        newValues.setName("New");
        newValues.setDescription("Desc");
        newValues.setPrice(100);
        newValues.setQuantity(2);
        newValues.setProductLink("link");
        newValues.setTags(List.of("Tech"));

        when(repository.findWishById(1)).thenReturn(existing);
        when(repository.updateWish(1, existing)).thenReturn(true);

        Wish result = service.updateWish(1, newValues);

        assertNotNull(result);
        assertEquals("New", result.getName());
        assertEquals("Desc", result.getDescription());
        assertEquals(100, result.getPrice());
        assertEquals(2, result.getQuantity());
        assertEquals("link", result.getProductLink());
        assertEquals(List.of("Tech"), result.getTags());
    }

    @Test
    void updateWish_returnsNull_whenNotFound() {
        when(repository.findWishById(1)).thenReturn(null);

        Wish result = service.updateWish(1, new Wish());

        assertNull(result);
    }

    @Test
    void updateWish_returnsNull_whenUpdateFails() {
        Wish existing = new Wish();
        existing.setWishId(1);

        when(repository.findWishById(1)).thenReturn(existing);
        when(repository.updateWish(1, existing)).thenReturn(false);

        Wish result = service.updateWish(1, new Wish());

        assertNull(result);
    }

    @Test
    void deleteWish_returnsDeleted_whenSuccess() {
        Wish existing = new Wish();
        existing.setWishId(1);

        when(repository.findWishById(1)).thenReturn(existing);
        when(repository.deleteWishById(1)).thenReturn(true);

        Wish result = service.deleteWish(1);

        assertNotNull(result);
        assertEquals(1, result.getWishId());
    }

    @Test
    void deleteWish_returnsNull_whenNotFound() {
        when(repository.findWishById(1)).thenReturn(null);

        Wish result = service.deleteWish(1);

        assertNull(result);
    }

    @Test
    void deleteWish_returnsNull_whenDeleteFails() {
        Wish existing = new Wish();
        existing.setWishId(1);

        when(repository.findWishById(1)).thenReturn(existing);
        when(repository.deleteWishById(1)).thenReturn(false);

        Wish result = service.deleteWish(1);

        assertNull(result);
    }

    @Test
    void getWishes_callsRepository() {
        service.getWishes();
        verify(repository).getAllWishes();
    }

    @Test
    void addWishes_callsRepository() {
        Wish wish = new Wish();
        service.addWishes(wish);
        verify(repository).addWish(wish);
    }

    @Test
    void getTags_callsRepository() {
        service.getTags();
        verify(repository).getTags();
    }
}