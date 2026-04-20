package com.example.wish.controller;

import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import com.example.wish.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishListService wishListService;

    @MockBean
    private WishService wishService;

    // ─────────────────────────────────────────────
    // GET ALL WISHLISTS
    // ─────────────────────────────────────────────

    @Test
    void getAllWishLists_returnsView() throws Exception {
        when(wishListService.findWishListsByUserId(1))
                .thenReturn(List.of(new WishList()));

        mockMvc.perform(get("/users/1/wishlists"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlists"))
                .andExpect(model().attributeExists("wishLists"))
                .andExpect(model().attributeExists("userId"));
    }

    // ─────────────────────────────────────────────
    // ADD WISHLIST PAGE
    // ─────────────────────────────────────────────

    @Test
    void addWishList_returnsView() throws Exception {
        mockMvc.perform(get("/users/1/wishlists/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addWishList"))
                .andExpect(model().attributeExists("wishList"))
                .andExpect(model().attributeExists("userId"));
    }

    // ─────────────────────────────────────────────
    // SAVE WISHLIST
    // ─────────────────────────────────────────────

    @Test
    void saveWishList_redirects() throws Exception {
        mockMvc.perform(post("/users/1/wishlists/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/1/wishlists"));
    }

    // ─────────────────────────────────────────────
    // EDIT PAGE (FOUND)
    // ─────────────────────────────────────────────

    @Test
    void editWishList_returnsView_whenFound() throws Exception {
        when(wishListService.findWishListByIdAndUserId(1, 1))
                .thenReturn(new WishList());

        mockMvc.perform(get("/users/1/wishlists/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("editWishList"));
    }

    // ─────────────────────────────────────────────
    // EDIT PAGE (NOT FOUND)
    // ─────────────────────────────────────────────

    @Test
    void editWishList_throwsException_whenNotFound() throws Exception {
        when(wishListService.findWishListByIdAndUserId(1, 1))
                .thenReturn(null);

        mockMvc.perform(get("/users/1/wishlists/1/edit"))
                .andExpect(status().isInternalServerError());
    }

    // ─────────────────────────────────────────────
    // VIEW SINGLE WISHLIST
    // ─────────────────────────────────────────────

    @Test
    void getWishList_returnsView() throws Exception {
        when(wishListService.findWishListByIdAndUserId(1, 1))
                .thenReturn(new WishList());

        when(wishService.getWishesByWishListId(1))
                .thenReturn(List.of());

        mockMvc.perform(get("/users/1/wishlists/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist"))
                .andExpect(model().attributeExists("wishList"))
                .andExpect(model().attributeExists("wishes"));
    }

    // ─────────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────────

    @Test
    void deleteWishList_redirects() throws Exception {
        when(wishListService.findWishListByIdAndUserId(1, 1))
                .thenReturn(new WishList());

        mockMvc.perform(post("/users/1/wishlists/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/1/wishlists"));
    }
}