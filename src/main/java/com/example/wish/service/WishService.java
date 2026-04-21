package com.example.wish.service;

import com.example.wish.model.Wish;
import com.example.wish.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wish> getWishes() {
        return wishRepository.getAllWishes();
    }

    public void addWishes(Wish wish) {
        wishRepository.addWish(wish);
    }

    public List<String> getTags() {
        return wishRepository.getTags();
    }

    public Wish findWishById(int wishId) {
        return wishRepository.findWishById(wishId);
    }

    public List<Wish> getWishesByWishListId(int wishListId) {
        return wishRepository.findWishesByWishListId(wishListId);
    }

    public Wish updateWish(int wishId, Wish newValues) {
        Wish existing = wishRepository.findWishById(wishId);

        if (existing == null) {
            return null;
        }

        if (newValues.getName() != null) existing.setName(newValues.getName());
        if (newValues.getDescription() != null) existing.setDescription(newValues.getDescription());
        if (newValues.getPrice() != 0) existing.setPrice(newValues.getPrice());
        if (newValues.getQuantity() != 0) existing.setQuantity(newValues.getQuantity());
        if (newValues.getProductLink() != null) existing.setProductLink(newValues.getProductLink());
        if (newValues.getTags() != null) existing.setTags(newValues.getTags());

        boolean updated = wishRepository.updateWish(wishId, existing);

        return updated ? existing : null;
    }

    public Wish deleteWish(int wishId) {
        Wish existing = wishRepository.findWishById(wishId);

        if (existing == null) {
            return null;
        }

        boolean deleted = wishRepository.deleteWishById(wishId);

        return deleted ? existing : null;
    }
}