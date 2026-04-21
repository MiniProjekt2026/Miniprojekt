package com.example.wish.controller;

import com.example.wish.model.Wish;
import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import com.example.wish.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlists/{wishListId}/wishes")
public class WishController {

    private final WishService wishService;
    private final WishListService wishListService;

    public WishController(WishService wishService, WishListService wishListService) {
        this.wishService = wishService;
        this.wishListService = wishListService;
    }

    private int resolveUserId(int wishListId) {
        WishList wl = wishListService.findWishListById(wishListId);
        return (wl != null) ? wl.getUserId() : 0;
    }

    @GetMapping("/add")
    public String addWishes(@PathVariable int wishListId, Model model) {
        Wish wish = new Wish();
        wish.setWishListId(wishListId);

        model.addAttribute("wish", wish);
        model.addAttribute("tags", wishService.getTags());
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", resolveUserId(wishListId));

        return "addWish";
    }

    @PostMapping("/save")
    public String saveWishes(@PathVariable int wishListId,
                             @ModelAttribute("wish") Wish wish) {
        wish.setWishListId(wishListId);
        wishService.addWishes(wish);

        int userId = resolveUserId(wishListId);
        return "redirect:/users/" + userId + "/wishlists/" + wishListId;
    }

    @GetMapping("/{wishId}")
    public String getWishById(@PathVariable int wishListId,
                              @PathVariable int wishId,
                              Model model) {
        Wish wish = wishService.findWishById(wishId);

        if (wish == null) {
            return "redirect:/error/404";
        }

        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", resolveUserId(wishListId));

        return "wish";
    }

    @GetMapping("/edit/{wishId}")
    public String editWish(@PathVariable int wishListId,
                           @PathVariable int wishId,
                           Model model) {
        Wish wish = wishService.findWishById(wishId);

        if (wish == null) {
            throw new IllegalArgumentException("Ønske findes ikke");
        }

        model.addAttribute("wish", wish);
        model.addAttribute("allTags", wishService.getTags());
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", resolveUserId(wishListId));

        return "editWish";
    }

    @PostMapping("/{wishId}/update")
    public String updateWish(@PathVariable int wishListId,
                             @PathVariable int wishId,
                             // FIX: missing explicit name caused binding to fail silently
                             @ModelAttribute("wish") Wish wish) {

        Wish updated = wishService.updateWish(wishId, wish);

        if (updated == null) {
            throw new IllegalArgumentException("Kunne ikke opdatere wish");
        }

        int userId = resolveUserId(wishListId);
        return "redirect:/users/" + userId + "/wishlists/" + wishListId;
    }

    @PostMapping("/{wishId}/delete")
    public String deleteWish(@PathVariable int wishListId,
                             @PathVariable int wishId) {

        Wish wish = wishService.findWishById(wishId);

        if (wish == null || wish.getWishListId() != wishListId) {
            throw new IllegalArgumentException("Ugyldig wish");
        }

        wishService.deleteWish(wishId);

        int userId = resolveUserId(wishListId);
        return "redirect:/users/" + userId + "/wishlists/" + wishListId;
    }

    @GetMapping("/{wishId}/tags")
    public String showTags(@PathVariable int wishListId,
                           @PathVariable int wishId,
                           Model model) {

        Wish wish = wishService.findWishById(wishId);

        if (wish == null) {
            throw new IllegalArgumentException("Wish findes ikke");
        }

        model.addAttribute("wish", wish);
        model.addAttribute("tags", wishService.getTags());
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("userId", resolveUserId(wishListId));

        return "tags";
    }
}