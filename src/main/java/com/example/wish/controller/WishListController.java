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


    @GetMapping("/edit/{wishListId}")
    public String editWishList(@PathVariable int wishListId, Model model) {
        WishList wishList = wishListService.findWishListById(wishListId);

        if (wishList == null) {
            throw new IllegalArgumentException("Wishlist findes ikke");
        }

        model.addAttribute("wishList", wishList);
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


    @GetMapping("/{name}/edit")
    public String editWishList(@PathVariable String name, Model model) {
        WishList wishList = wishListService.findWishListByName(name);

        if (wishList == null) {
            throw new IllegalArgumentException("Der findes ingen ønskeliste med det navn");
        }

        model.addAttribute("wishList", wishList);
        model.addAttribute("oldName", name);

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
