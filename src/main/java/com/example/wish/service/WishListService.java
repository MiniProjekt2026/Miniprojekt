package com.example.wish.service;


import com.example.wish.model.WishList;
import com.example.wish.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> getAttractions() {
        return wishListRepository.getAllWishes();
    }

    public List<WishList> getWishes(){
        return wishListRepository.getAllWishes();
    }

    public void addWishes(){

    }

    public List<String> getTags(){
        return wishListRepository.getTags();
    }

    public WishList findWishListByName(String name){
        String normalizedInput = normalize(name);
        WishList wishlist = null;

        for (WishList w : wishListRepository.getAllWishes()){
            if(normalize(w.getName()).equals(normalizedInput)){
                wishlist = w;
                break;
            }
        }
        return wishlist;
    }

    private String normalize (String name){
        return name.toLowerCase().replaceAll("\\s+", "");
    }

    public void updateWishes(){

    }

    public void deleteWish(){

    }
}
