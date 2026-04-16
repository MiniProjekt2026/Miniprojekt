package com.example.wish.model;

public class WishList {
    private int wishlist_id;
    private String name;

    public WishList(){

    }

    public WishList(int wishlist_id, String name){
        this.wishlist_id=wishlist_id;
        this.name=name;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
