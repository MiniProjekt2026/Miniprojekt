package com.example.wish.controller;

import com.example.wish.model.User;
import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("wishes")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/add")
    public void addWishes(Model model) {
        model.addAttribute("wishList", new WishList());
        model.addAttribute("user", new User());
        model.addAttribute("tags", wishListService.getTags());
    }

    @PostMapping("/save")
    public String saveWishes(@ModelAttribute WishList wishList) {
        wishListService.addWishes(wishList);
        return "redirect:/wishes";
    }

//    @GetMapping("/wishlist/{userid}/{wishid}")
//    public String getWishByWishId(@PathVariable int userid, @PathVariable int wishid, Model model) {
//
//        WishList wish = wishListService.findWishByUserIdAndWishId(userid, wishid);
//
//        model.addAttribute("wish", wish);
//        return "wish";
//    }

    @GetMapping("/wishlist/{userid}")
    public String getAllWishesByUsername(@PathVariable int userid, Model model) {

        List<WishList> wishes = wishListService.findWishListByUserId(userid);

        if (wishes.isEmpty()) {
            throw new IllegalArgumentException("Ingen ønskeliste fundet for det userId");
        }

        model.addAttribute("wishes", wishes);
        return "wishes";
    }


    @GetMapping("/{name}/tags")
    public void showTags() {

    }

    @GetMapping("/{name}/edit")
    public void editWish() {

    }

    @PostMapping("/update")
    public void updateWishes() {
    }

    @PostMapping("/delete/{name}")
    public void deleteWish() {
    }
}
