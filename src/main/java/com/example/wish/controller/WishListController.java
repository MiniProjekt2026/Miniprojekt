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


        model.addAttribute("wish_list", wishList);
        model.addAttribute("user_id", userId);

        return "addWishList";
    }

    @PostMapping("/save")
    public String saveWishList(@PathVariable int userId,
                               @ModelAttribute("wish_list") WishList wishList){

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

    @GetMapping("")
    public String getAllWishLists(@PathVariable int userId, Model model) {
        model.addAttribute("wishlists", wishListService.findWishListsByUserId(userId));
        model.addAttribute("userId", userId);
        return "wishlists";
    }

    @GetMapping("/{wishListId}")
    public String getWishListByWishListId(@PathVariable int userId,
                                          @PathVariable int wishListId,
                                          Model model) {
        WishList wishList = wishListService.findWishListById(wishListId);

        model.addAttribute("wishlist", wishList);
        return "wishlist";
    }

    @PostMapping("/{wishListId}/update")
    public String updateWishList(@ModelAttribute WishList wishList) {
        WishList existing = wishListService.findWishListById(wishList.getWishListId());

        if (existing == null) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        int userId = existing.getUserId();

        WishList updatedWishList = wishListService.updateWishList(
                wishList.getWishListId(),
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
        WishList deleted = wishListService.deleteWishList(wishListId);

        if (deleted == null) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        return "redirect:/users/" + userId + "/wishlists";
    }
}
