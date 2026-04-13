package com.example.onske.controller;

import com.example.onske.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Wishes")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService){
        this.wishListService = wishListService;
    }

    @GetMapping("/add")
    public void addWishes(){

    }

    @PostMapping("/save")
    public void saveWishes(){

    }

    @GetMapping("/wishlist")
    public void getAllWishes(){

    }

    @GetMapping("/{name}")
    public void getWishesByName(){

    }

    @GetMapping ("/{name}/tags")
    public void showTags(){

    }

    @GetMapping("/{name}/edit")
    public void editWish(){

    }

    @PostMapping("/update")
    public void updateWishes(){
    }

    @PostMapping("/delete/{name}")
    public void deleteWish(){

    }

}
