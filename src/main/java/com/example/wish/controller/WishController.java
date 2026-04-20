package com.example.wish.controller;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import com.example.wish.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlists/{wishListId}/wishes")
public class WishController {

    private final WishService wishService;
    private final WishListService wishListService;

    public WishController(WishService wishService, WishListService wishListService) {
        this.wishService = wishService;
        this.wishListService = wishListService;
    }

    @GetMapping("/add")
    public String addWishes(@PathVariable int wishListId, Model model) {
        Wish wish = new Wish();
        wish.setWishListId(wishListId);

        WishList wishList = wishListService.findWishListById(wishListId);
        int userId = (wishList != null) ? wishList.getUserId() : 0;

        model.addAttribute("wish", wish);
        model.addAttribute("tags", wishService.getTags());
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", userId); // FIX: was missing

        return "addWish";
    }

    @PostMapping("/save")
    public String saveWishes(@PathVariable int wishListId,
                             @ModelAttribute("wish") Wish wish) {

        wish.setWishListId(wishListId);
        wishService.addWishes(wish);

        WishList wishList = wishListService.findWishListById(wishListId);
        int userId = (wishList != null) ? wishList.getUserId() : 0;
        return "redirect:/users/" + userId + "/wishlists/" + wishListId;
    }

    @GetMapping("/{wishId}")
    public String getWishById(@PathVariable int wishListId,
                              @PathVariable int wishId,
                              Model model) {
        Wish wish = wishService.findWishById(wishId);

        if (wish == null) {
            throw new IllegalArgumentException("Wish findes ikke");
        }

        WishList wishList = wishListService.findWishListById(wishListId);
        int userId = (wishList != null) ? wishList.getUserId() : 0;

        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", userId);
        return "wish";
    }

    @GetMapping("/{wishId}/tags")
    public String showTags(@PathVariable int wishListId,
                           @PathVariable int wishId,
                           Model model) {

        Wish wish = wishService.findWishById(wishId);

        if (wish == null) {
            throw new IllegalArgumentException("Wish findes ikke");
        }

        WishList wishList = wishListService.findWishListById(wishListId);
        int userId = (wishList != null) ? wishList.getUserId() : 0;

        model.addAttribute("wish", wish);
        model.addAttribute("tags", wishService.getTags());
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", userId);

        return "tags";
    }

    @GetMapping("/edit/{wishId}")
    public String editWish(@PathVariable int wishListId,
                           @PathVariable int wishId,
                           Model model) {
        Wish wish = wishService.findWishById(wishId);

        if (wish == null) {
            throw new IllegalArgumentException("Ønske findes ikke");
        }

        WishList wishList = wishListService.findWishListById(wishListId);
        int userId = (wishList != null) ? wishList.getUserId() : 0;

        model.addAttribute("wish", wish);
        model.addAttribute("allTags", wishService.getTags());
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", userId);

        return "editWish";
    }

    @PostMapping("/{wishId}/update")
    public String updateWish(@PathVariable int wishListId,
                             @PathVariable int wishId,
                             @ModelAttribute Wish wish) {

        Wish updated = wishService.updateWish(wishId, wish);

        if (updated == null) {
            throw new IllegalArgumentException("Kunne ikke opdatere wish");
        }

        WishList wishList = wishListService.findWishListById(wishListId);
        int userId = (wishList != null) ? wishList.getUserId() : 0;
        return "redirect:/users/" + userId + "/wishlists/" + wishListId;
    }

    @PostMapping("/{wishId}/delete")
    public String deleteWish(@PathVariable int wishListId,
                             @PathVariable int wishId) {

        Wish wish = wishService.findWishById(wishId);

        if (wish == null || wish.getWishListId() != wishListId) {
            throw new IllegalArgumentException("Ugyldig wish");
        }

        Wish deleted = wishService.deleteWish(wishId);

        if (deleted == null) {
            throw new IllegalArgumentException("Kunne ikke slette wish");
        }

        WishList wishList = wishListService.findWishListById(wishListId);
        int userId = (wishList != null) ? wishList.getUserId() : 0;

        return "redirect:/users/" + userId + "/wishlists/" + wishListId;
    }
}