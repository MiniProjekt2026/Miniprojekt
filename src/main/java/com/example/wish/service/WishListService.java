package com.example.wish.service;

import com.example.wish.model.WishList;
import com.example.wish.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService{

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
