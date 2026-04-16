package com.example.wish.model;

import java.util.ArrayList;
import java.util.List;

public class Wish {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private List<String> tags = new ArrayList<>();
    private String productLink;
    private int wishListId;

    public Wish() {
    }

    public Wish(String name, String description, double price, int quantity, List<String> tags, String productLink, int wishListId){
        this.name=name;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
        this.tags=(tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
        this.productLink=productLink;
        this.wishListId=wishListId;
    }

    public Wish(int id, String name, String description, double price, int quantity, List<String> tags, String productLink, int wishListId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.tags = (tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
        this.productLink = productLink;
        this.wishListId = wishListId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
//:)
