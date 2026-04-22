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
        return wishListRepository.findWishListById(wishListId);
    }

    public List<WishList> findWishListsByUserId(int userId) {
        return wishListRepository.findWishListsByUserId(userId);
    }

    public WishList findWishListByIdAndUserId(int userId, int wishListId) {
        return wishListRepository.findWishListByIdAndUserId(userId, wishListId);
    }

    public WishList updateWishList(int wishListId, WishList newValues) {
        WishList existing = wishListRepository.findWishListById(wishListId);

        if (existing == null) {
            return null;
        }

        if (newValues.getName() != null) {
            existing.setName(newValues.getName());
        }

        boolean updated = wishListRepository.updateWishList(wishListId, existing);

        if (!updated) {
            return null;
        }

        return existing;
    }

    public WishList deleteWishList(int wishListId) {
        WishList existing = wishListRepository.findWishListById(wishListId);

        if (existing == null) {
            return null;
        }

        boolean deleted = wishListRepository.deleteWishList(wishListId);

        if (!deleted) {
            return null;
        }

        return existing;
    }
    public void addWishList(WishList wishList) {
        wishListRepository.addWishList(wishList);
    }
    //:)
}
