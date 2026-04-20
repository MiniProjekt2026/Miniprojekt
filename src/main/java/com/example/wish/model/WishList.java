package com.example.wish.model;

import java.util.ArrayList;
import java.util.List;

public class WishList {
    private int wishListId;
    private int userId;
    private String name;
    private List<Wish> wishes = new ArrayList<>();

    public WishList() {
    }

    public WishList(int wishListId, int userId, String name) {
        this.wishListId = wishListId;
        this.userId = userId;
        this.name = name;
        this.wishes = new ArrayList<>();
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = (wishes != null) ? wishes : new ArrayList<>();
    }
}