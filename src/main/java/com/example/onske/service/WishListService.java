package com.example.onske.service;


import com.example.onske.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
    }

    public void getWishes(){

    }

    public void addWishes(){

    }

    public List<String> getTags(){

    }

    public List<String> getTagsByWishName(){

    }

    public void updateWishes(){

    }

    public void deleteWish(){

    }
}
