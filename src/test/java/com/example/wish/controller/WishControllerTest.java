package com.example.wish.controller;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import com.example.wish.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishController.class)
class WishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishService wishService;

    @MockitoBean
    private WishListService wishListService;

    @Test
    void addWish_returnsView() throws Exception {

        when(wishListService.findWishListById(1))
                .thenReturn(new WishList());

        when(wishService.getTags())
                .thenReturn(List.of("tag1", "tag2"));

        mockMvc.perform(get("/wishlists/1/wishes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addWish"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attributeExists("wishListId"))
                .andExpect(model().attributeExists("userId"));
    }

    @Test
    void saveWish_redirects() throws Exception {

        when(wishListService.findWishListById(1))
                .thenReturn(new WishList());

        mockMvc.perform(post("/wishlists/1/wishes/save")
                        .flashAttr("wish", new Wish()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/0/wishlists/1"));
    }

    @Test
    void getWish_returnsView_whenFound() throws Exception {

        Wish wish = new Wish();
        wish.setWishListId(1);

        when(wishService.findWishById(1))
                .thenReturn(wish);

        when(wishListService.findWishListById(1))
                .thenReturn(new WishList());

        mockMvc.perform(get("/wishlists/1/wishes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("wish"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attributeExists("wishListId"))
                .andExpect(model().attributeExists("userId"));
    }

    @Test
    void getWish_throwsException_whenNotFound() throws Exception {

        when(wishService.findWishById(1)).thenReturn(null);

        mockMvc.perform(get("/wishlists/1/wishes/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error/404"));
    }

    @Test
    void editWish_returnsView_whenFound() throws Exception {

        Wish wish = new Wish();
        wish.setWishListId(1);

        when(wishService.findWishById(1))
                .thenReturn(wish);

        when(wishService.getTags())
                .thenReturn(List.of("tag1"));

        when(wishListService.findWishListById(1))
                .thenReturn(new WishList());

        mockMvc.perform(get("/wishlists/1/wishes/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("editWish"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attributeExists("allTags"))
                .andExpect(model().attributeExists("userId"));
    }

    @Test
    void updateWish_redirects() throws Exception {

        Wish updated = new Wish();
        updated.setWishListId(1);

        when(wishService.updateWish(1, updated))
                .thenReturn(updated);

        when(wishListService.findWishListById(1))
                .thenReturn(new WishList());

        mockMvc.perform(post("/wishlists/1/wishes/1/update")
                        .flashAttr("wish", updated))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteWish_redirects() throws Exception {

        Wish wish = new Wish();
        wish.setWishListId(1);

        when(wishService.findWishById(1))
                .thenReturn(wish);

        when(wishListService.findWishListById(1))
                .thenReturn(new WishList());

        mockMvc.perform(post("/wishlists/1/wishes/1/delete"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void showTags_returnsView() throws Exception {

        Wish wish = new Wish();
        wish.setWishListId(1);

        when(wishService.findWishById(1))
                .thenReturn(wish);

        when(wishService.getTags())
                .thenReturn(List.of("tag1", "tag2"));

        when(wishListService.findWishListById(1))
                .thenReturn(new WishList());

        mockMvc.perform(get("/wishlists/1/wishes/1/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attributeExists("userId"));
    }
}