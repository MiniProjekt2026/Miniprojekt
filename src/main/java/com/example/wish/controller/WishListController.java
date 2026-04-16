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
    public String updateWishList(@RequestParam String oldname,
                                 @ModelAttribute WishList wishList) {

        WishList updatedWishList = wishListService.updateWishList(oldname, wishList);

        if (updatedWishList == null) {
            throw new IllegalArgumentException("Kunne ikke opdatere ønskelisten");
        }

        return "redirect:/wishes/" + updatedWishList.getName();
    }
}
