package com.example.wish.controller;

import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }


    @GetMapping("/edit/{wishListId}")
    public String editWishList(@PathVariable int wishListId, Model model) {
        WishList wishList = wishListService.findWishListById(wishListId);

        if (wishList == null) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        model.addAttribute("wishList", wishList);
        return "editWishList";
    }

    @PostMapping("/update")
    public String updateWishList(@ModelAttribute WishList wishList) {
        WishList updatedWishList = wishListService.updateWishList(
                wishList.getWishListId(),
                wishList
        );

        if (updatedWishList == null) {
            throw new IllegalArgumentException("Wishlist kunne ikke opdateres");
        }

        return "redirect:/wishlists/user/" + updatedWishList.getUserId();
    }
}
}
