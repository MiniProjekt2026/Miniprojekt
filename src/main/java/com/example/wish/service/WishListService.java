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

    public WishList findWishListById(int wishListId) {
        return wishListRepository.findWishListById(wishListId);
    }

    public WishList updateWishList(int wishListId, WishList newValues) {
        WishList existing = wishListRepository.findWishListById(wishListId);

        if (existing == null) {
            return null;
        }

        existing.setName(newValues.getName());

        boolean updated = wishListRepository.updateWishList(wishListId, existing);

        if (!updated) {
            return null;
        }

        return existing;
    }
}
