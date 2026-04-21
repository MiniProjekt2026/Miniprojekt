package com.example.wish.model;

import java.util.ArrayList;
import java.util.List;

public class Wish {
    private int wishId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private List<String> tags = new ArrayList<>();
    private String productLink;
    private boolean reserved;
    private int wishListId;

    public Wish() {
    }

    public Wish(String name, String description, double price, int quantity, List<String> tags, String productLink, boolean reserved, int wishListId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.tags = (tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
        this.productLink = productLink;
        this.reserved=reserved;
        this.wishListId = wishListId;
    }

    public Wish(int wishId, String name, String description, double price, int quantity, List<String> tags, String productLink, boolean reserved, int wishListId) {
        this.wishId = wishId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.tags = (tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
        this.productLink = productLink;
        this.reserved=reserved;
        this.wishListId = wishListId;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = (tags != null) ? tags : new ArrayList<>();
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }
}