package com.example.wish.controller;

import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import com.example.wish.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shared")
public class SharedWishListController {
    private final WishListService wishListService;
    private final WishService wishService;

    public SharedWishListController(WishListService wishListService, WishService wishService) {
        this.wishListService = wishListService;
        this.wishService = wishService;
    }

    @GetMapping("/wishlists/{wishListId}")
    public String getSharedWishList(@PathVariable int wishListId, Model model) {
        WishList wishList = wishListService.findWishListById(wishListId);

        if (wishList == null) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        model.addAttribute("wishList", wishList);
        model.addAttribute("wishes", wishService.getWishesByWishListId(wishListId));

        return "sharedWishList";
    }

    @PostMapping("/wishlists/{wishListId}/wishes/{wishId}/reserve")
    public String reserveWish(@PathVariable int wishListId, @PathVariable int wishId){
        wishService.reservedWishSetTrue(wishId);
        return "redirect:/shared/wishlists" + wishListId;
    }

    @PostMapping("/wishlists/{wishListId}/wishes/{wishId}/reserve")
    public String removeReserveWish(@PathVariable int wishListId, @PathVariable int wishId){
        wishService.reservedWishSetFalse(wishId);
        return "redirect:/shared/wishlists" + wishListId;
    }
}
