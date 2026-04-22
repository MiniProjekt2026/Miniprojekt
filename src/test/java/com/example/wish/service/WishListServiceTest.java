package com.example.wish.service;

import com.example.wish.model.WishList;
import com.example.wish.repository.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishListServiceTest {

    private WishListRepository repository;
    private WishListService service;

    @BeforeEach
    void setUp() {
        repository = mock(WishListRepository.class);
        service = new WishListService(repository);
    }

    @Test
    void updateWishList_returnsUpdated_whenSuccess() {
        WishList existing = new WishList();
        existing.setWishListId(1);
        existing.setName("Old");

        WishList newValues = new WishList();
        newValues.setName("New");

        when(repository.findWishListById(1)).thenReturn(existing);
        when(repository.updateWishList(1, existing)).thenReturn(true);

        WishList result = service.updateWishList(1, newValues);

        assertNotNull(result);
        assertEquals("New", result.getName());
    }

    @Test
    void updateWishList_returnsNull_whenNotFound() {
        when(repository.findWishListById(1)).thenReturn(null);

        WishList result = service.updateWishList(1, new WishList());

        assertNull(result);
    }

    @Test
    void updateWishList_returnsNull_whenUpdateFails() {
        WishList existing = new WishList();
        existing.setWishListId(1);

        when(repository.findWishListById(1)).thenReturn(existing);
        when(repository.updateWishList(1, existing)).thenReturn(false);

        WishList result = service.updateWishList(1, new WishList());

        assertNull(result);
    }

    @Test
    void deleteWishList_returnsDeleted_whenSuccess() {
        WishList existing = new WishList();
        existing.setWishListId(1);

        when(repository.findWishListById(1)).thenReturn(existing);
        when(repository.deleteWishList(1)).thenReturn(true);

        WishList result = service.deleteWishList(1);

        assertNotNull(result);
        assertEquals(1, result.getWishListId());
    }

    @Test
    void deleteWishList_returnsNull_whenNotFound() {
        when(repository.findWishListById(1)).thenReturn(null);

        WishList result = service.deleteWishList(1);

        assertNull(result);
    }

    @Test
    void deleteWishList_returnsNull_whenDeleteFails() {
        WishList existing = new WishList();
        existing.setWishListId(1);

        when(repository.findWishListById(1)).thenReturn(existing);
        when(repository.deleteWishList(1)).thenReturn(false);

        WishList result = service.deleteWishList(1);

        assertNull(result);
    }

    @Test
    void addWishList_callsRepository() {
        WishList wl = new WishList();

        service.addWishList(wl);

        verify(repository).addWishList(wl);
    }
}