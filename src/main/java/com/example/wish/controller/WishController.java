package com.example.wish.controller;

import com.example.wish.model.Wish;
import com.example.wish.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("wishlists/{wishListId}/wishes")
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/add")
    public String addWishes(@PathVariable int wishListId, Model model) {
        Wish wish = new Wish();
        wish.setWishListId(wishListId); // important

        model.addAttribute("wish", wish);
        model.addAttribute("tags", wishService.getTags());
        model.addAttribute("wishListId", wishListId);

        return "addWish";
    }

    @PostMapping("/save")
    public String saveWishes(@PathVariable int wishListId,
                             @ModelAttribute("wish") Wish wish) {

        wish.setWishListId(wishListId); // enforce relationship
        wishService.addWishes(wish);

        return "redirect:/wishlists/" + wishListId;
    }

//    @GetMapping("/wishlist/{userid}/{wishid}")
//    public String getWishByWishId(@PathVariable int userid, @PathVariable int wishid, Model model) {
//
//        Wish wish = wishListService.findWishByUserIdAndWishId(userid, wishid);
//
//        model.addAttribute("wish", wish);
//        return "wish";
//    }

//    @GetMapping("/wishlist/{userid}")
//    public String getAllWishesByUsername(@PathVariable int userid, Model model) {
//
//        List<Wish> wishes = wishService.findWishByWishListId(userid);
//
//        if (wishes.isEmpty()) {
//            throw new IllegalArgumentException("Ingen ønskeliste fundet for det userId");
//        }
//
//        model.addAttribute("wishes", wishes);
//        return "wishes";
//    }


    @GetMapping("/{wishId}/tags")
    public String showTags(@PathVariable int wishListId,
                           @PathVariable int wishId) {
        return "tags";
    }

    @GetMapping("/{wishId}/edit")
    public String editWish(@PathVariable int wishListId,
                           @PathVariable int wishId,
                           Model model) {

        List<Wish> wish = wishService.getWishById(wishId);
        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);

        return "editWish";
    }

    @PostMapping("/{wishId}/update")
    public String updateWish(@PathVariable int wishListId,
                               @PathVariable int wishId,
                               @ModelAttribute Wish wish, String oldName) {

        wishService.updateWish(oldName, wish);

        return "redirect:/wishlists/" + wishListId;
    }

    @PostMapping("/{wishId}/delete")
    public String deleteWish(@PathVariable int wishListId,
                             @PathVariable int wishId) {

        wishService.deleteWish(String.valueOf(wishId));

        return "redirect:/wishlists/" + wishListId;
    }
}