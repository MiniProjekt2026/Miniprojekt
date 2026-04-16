package com.example.wish.controller;

import com.example.wish.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wishlists")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }
    @GetMapping("")
    public String getAllWishLists(Model model) {
        model.addAttribute("wishlists", wishListService.getAllWishLists());
        return "wishlists";
    }
    @GetMapping("/{wishListId}")
    public String getWishListByWishListId(@PathVariable int wishListId, Model model) {
        WishList wishList = wishListService.findWishListById(wishListId);

        model.addAttribute("wishlist", wishList);
       return "wishlist";
    }
}
