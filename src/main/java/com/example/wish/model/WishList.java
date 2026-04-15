package com.example.wish.model;

import java.util.ArrayList;
import java.util.List;

public class WishList {
    private int wish_id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private List<String> tags;
    private String productLink;
    private int userId;

    public WishList() {
    }

    public WishList(String name, String description, double price, int quantity, List<String> tags, String productLink, int userId){
        this.name=name;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
        this.tags=(tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
        this.productLink=productLink;
        this.userId=userId;
    }

    public WishList(int wish_id, String name, String description, double price, int quantity, List<String> tags, String productLink, int userId){
        this.wish_id=wish_id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
        this.tags=(tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
        this.productLink=productLink;
        this.userId=userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWish_id() {
        return wish_id;
    }

    public void setWish_id(int wish_id) {
        this.wish_id = wish_id;
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
        this.tags = tags;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }
}
