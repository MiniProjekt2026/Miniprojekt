package com.example.onske.model;

import java.util.List;

public class WishList {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private List<String> tags;
    private String productLink;

    public WishList() {
    }

    public WishList(String name, String description, double price, int quantity, List<String> tags, String productLink){
      this.name=name;
      this.description=description;
      this.price=price;
      this.quantity=quantity;
      this.tags=tags;
      this.productLink=productLink;
    }

    public WishList(int id, String name, String description, double price, int quantity, List<String> tags, String productLink){
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
        this.tags=tags;
        this.productLink=productLink;
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
