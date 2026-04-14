package com.example.wish.controller;

import com.example.wish.model.User;
import com.example.wish.model.WishList;
import com.example.wish.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("wishes")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/add")
    public void addWishes() {

    }

    @PostMapping("/save")
    public void saveWishes() {

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
            throw new IllegalArgumentException("Ingen ønskeliste fundet for det username");
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
