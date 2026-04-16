package com.example.wish.service;

import com.example.wish.model.WishList;
import com.example.wish.repository.WishListRepository;

import java.util.List;

public class WishListService{

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> getAllWishLists() {return wishListRepository.getAllWishLists();}

    public WishList findWishListByName(String name) {
        String normalizedInput = normalize(name);
        WishList wishList = null;

        for (WishList wl : wishListRepository.getAllWishLists()) {
            if (normalize(wl.getName()).equals(normalizedInput)) {
                wishList = wl;
                break;
            }
        }
        return wishList;
    }
    private String normalize(String name) {
        return name.toLowerCase().replaceAll("\\s+", "");
    }
}
