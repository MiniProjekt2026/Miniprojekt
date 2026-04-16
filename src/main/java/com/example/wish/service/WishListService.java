package com.example.wish.service;

import com.example.wish.model.WishList;
import com.example.wish.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService{

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> getAllWishLists() {return wishListRepository.getAllWishLists();}

    public WishList findWishListById(int wishListId) {

        WishList wishList = null;

        for (WishList wl : wishListRepository.getAllWishLists()) {
            if (wl.getId() = wishListId) {
                wishList = wl;
                break;
            }
        }
        return wishList;
    }
}
