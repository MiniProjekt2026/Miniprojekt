package com.example.wish.service;

import com.example.wish.model.WishList;
import com.example.wish.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public WishList findWishListByName(String name) {
        return wishListRepository.findWishListByName(name);
    }

    public WishList updateWishList(String oldName, WishList wishList) {
        boolean updated = wishListRepository.updateWishList(oldName, wishList);

        if (!updated) {
            return null;
        }

        return wishListRepository.findWishListByName(wishList.getName());
    }
}