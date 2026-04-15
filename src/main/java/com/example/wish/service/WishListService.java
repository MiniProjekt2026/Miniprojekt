package com.example.wish.service;


import com.example.wish.model.WishList;
import com.example.wish.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> getWishes() {
        return wishListRepository.getAllWishes();
    }

    public void addWishes(WishList wishList) {
        wishListRepository.addWish(wishList);
    }

    public List<String> getTags() {
        return wishListRepository.getTags();
    }

    public WishList findWishListByName(String name) {
        String normalizedInput = normalize(name);
        WishList wishlist = null;

        for (WishList w : wishListRepository.getAllWishes()) {
            if (normalize(w.getName()).equals(normalizedInput)) {
                wishlist = w;
                break;
            }
        }
        return wishlist;
    }

    private String normalize(String name) {
        return name.toLowerCase().replaceAll("\\s+", "");
    }

    public void updateWishes() {

    }

    public WishList deleteWish(String name) {
        WishList wishList = findWishListByName(name);

        if (wishList == null) {
            return null;
        }

        boolean deleted = wishListRepository.deleteWish(name);

        if (!deleted) {
            return null;
        }

        return wishList;
    }

    public void getWishListByWishId() {

    }

    public List<WishList> findWishListByUserId(int userid) {
        List<WishList> result = new ArrayList<>();

        for (WishList w : wishListRepository.getAllWishes()) {
            if (w.getUserId() == userid) {
                result.add(w);
            }
        }
        return result;
    }

//    public WishList findWishByUserIdAndWishId(int userId, int wishId) {
//
//        WishList
//
//        return WishList;
//    }


}
