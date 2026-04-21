package com.example.wish.controller;

import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import com.example.wish.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishListService wishListService;

    @MockitoBean
    private WishService wishService;

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

    @Test
    void addWishList_returnsView() throws Exception {
        mockMvc.perform(get("/users/1/wishlists/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addWishList"))
                .andExpect(model().attributeExists("wishList"))
                .andExpect(model().attributeExists("userId"));
    }

    @Test
    void saveWishList_redirects() throws Exception {
        mockMvc.perform(post("/users/1/wishlists/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/1/wishlists"));
    }

    @Test
    void editWishList_returnsView_whenFound() throws Exception {
        when(wishListService.findWishListByIdAndUserId(1, 1))
                .thenReturn(new WishList());

        mockMvc.perform(get("/users/1/wishlists/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("editWishList"));
    }

    @Test
    void editWishList_throwsException_whenNotFound() throws Exception {
        when(wishListService.findWishListByIdAndUserId(1, 1))
                .thenReturn(null);

        mockMvc.perform(get("/users/1/wishlists/1/edit"))
                .andExpect(status().isInternalServerError());
    }

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

    @Test
    void deleteWishList_redirects() throws Exception {
        when(wishListService.findWishListByIdAndUserId(1, 1))
                .thenReturn(new WishList());

        mockMvc.perform(post("/users/1/wishlists/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/1/wishlists"));
    }
}