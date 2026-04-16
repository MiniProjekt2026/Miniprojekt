package com.example.wish.service;


import com.example.wish.model.Wish;
import com.example.wish.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Wish findWishByName(String name) {
        String normalizedInput = normalize(name);
        Wish wish = null;

        for (Wish w : wishRepository.getAllWishes()) {
            if (normalize(w.getName()).equals(normalizedInput)) {
                wish = w;
                break;
            }
        }
        return wish;
    }

    private String normalize(String name) {
        return name.toLowerCase().replaceAll("\\s+", "");
    }

    public Wish updateWish(String oldname, Wish newWishValues) {
        Wish existing = findWishByName(oldname);
        if(existing==null) return null;

        existing.setName(newWishValues.getName());
        existing.setDescription(newWishValues.getDescription());
        existing.setPrice(newWishValues.getPrice());
        existing.setQuantity(newWishValues.getQuantity());
        existing.setProductLink(newWishValues.getProductLink());

        boolean updated = wishRepository.updateWish(oldname, existing);
        if(!updated) return null;

        return existing;
    }

    public Wish deleteWish(String name) {
        Wish wish = findWishByName(name);

        if (wish == null) {
            return null;
        }

        boolean deleted = wishRepository.deleteWish(name);

        if (!deleted) {
            return null;
        }

        return wish;
    }

    public void getWishByWishId() {

    }

    public List<Wish> getWishById(int wishId) {
        List<Wish> result = new ArrayList<>();

        for (Wish w : wishRepository.getAllWishes()) {
            if (w.getWishListId() == wishId) {
                result.add(w);
            }
        }
        return result;
    }

//    public Wish findWishByUserIdAndWishId(int userId, int wishId) {
//
//        Wish
//
//        return Wish;
//    }


}
