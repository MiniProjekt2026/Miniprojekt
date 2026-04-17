package com.example.wish.controller;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users/{userId}/wishlists")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/add")
    public String addWishList(@PathVariable int userId, Model model) {
        WishList wishList = new WishList();
        wishList.setUserId(userId);


        model.addAttribute("wishList", wishList);
        model.addAttribute("userId", userId);

        return "addWishList";
    }

    @PostMapping("/save")
    public String saveWishList(@PathVariable int userId,
                               @ModelAttribute("wishList") WishList wishList){

        wishList.setUserId(userId); // enforce relationship
        wishListService.addWishList(wishList);

        return "redirect:/users/" + userId + "/wishlists";
    }


    @GetMapping("/{wishListId}/edit")
    public String editWishList(@PathVariable int userId, @PathVariable int wishListId, Model model) {
        WishList wishList = wishListService.findWishListByIdAndUserId(userId, wishListId);

        if (wishList == null) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        model.addAttribute("wishList", wishList);
        model.addAttribute("userId", userId);

        return "editWishList";
    }

    @GetMapping()
    public String getAllWishLists(@PathVariable int userId, Model model) {
        model.addAttribute("wishLists", wishListService.findWishListsByUserId(userId));
        model.addAttribute("userId", userId);
        return "wishlists";
    }

    @GetMapping("/{wishListId}")
    public String getWishListByWishListId(@PathVariable int userId,
                                          @PathVariable int wishListId,
                                          Model model) {
        WishList wishList = wishListService.findWishListById(wishListId);

        if (wishList == null) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        model.addAttribute("wishList", wishList);
        return "wishList";
    }

    @PostMapping("/{wishListId}/update")
    public String updateWishList(@PathVariable int userId,
                                 @PathVariable int wishListId,
                                 @ModelAttribute WishList wishList) {

        WishList existing = wishListService.findWishListById(wishListId);

        if (existing == null || existing.getUserId() != userId) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        WishList updatedWishList = wishListService.updateWishList(
                wishListId,
                wishList
        );

        if (updatedWishList == null) {
            throw new IllegalArgumentException("Wishlist kunne ikke opdateres");
        }

        return "redirect:/users/" + userId + "/wishlists";
    }

    @PostMapping("/{wishListId}/delete")
    public String deleteWishList(@PathVariable int userId,
                                 @PathVariable int wishListId) {

        WishList existing = wishListService.findWishListById(wishListId);

        if (existing == null || existing.getUserId() != userId) {
            throw new IllegalArgumentException("Ugyldig wishlist");
        }

        wishListService.deleteWishList(wishListId);

        return "redirect:/users/" + userId + "/wishlists";
    }
}
