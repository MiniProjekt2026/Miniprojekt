package com.example.wish.repository;

import com.example.wish.model.Wish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class WishRepositoryIntegrationTest {

    @Autowired
    private WishRepository repository;

    @Test
    void getAllWishes_returnsData() {
        List<Wish> wishes = repository.getAllWishes();

        assertFalse(wishes.isEmpty());
        assertEquals("Laptop", wishes.get(0).getName());
    }

    @Test
    void findWishesByWishListId_returnsCorrectData() {
        List<Wish> wishes = repository.findWishesByWishListId(1);

        assertEquals(1, wishes.size());
        assertEquals("Laptop", wishes.get(0).getName());
    }

    @Test
    void addWish_withTags_savesEverything() {
        Wish wish = new Wish();
        wish.setName("Phone");
        wish.setDescription("Smartphone");
        wish.setPrice(500);
        wish.setQuantity(1);
        wish.setProductLink("link");
        wish.setWishListId(1);
        wish.setTags(List.of("Tech"));

        repository.addWish(wish);

        List<Wish> wishes = repository.getAllWishes();

        assertTrue(
                wishes.stream().anyMatch(w -> w.getName().equals("Phone"))
        );
    }

    @Test
    void updateWish_updatesAndReplacesTags() {
        Wish wish = new Wish();
        wish.setName("Updated Laptop");
        wish.setDescription("Updated");
        wish.setPrice(2000);
        wish.setQuantity(1);
        wish.setProductLink("newlink");
        wish.setTags(List.of("UpdatedTag"));

        boolean result = repository.updateWish(1, wish);

        assertTrue(result);

        Wish updated = repository.findWishById(1);

        assertEquals("Updated Laptop", updated.getName());
        assertTrue(updated.getTags().contains("UpdatedTag"));
    }

    @Test
    void deleteWish_removesWish() {
        boolean deleted = repository.deleteWishById(1);

        assertTrue(deleted);

        Wish wish = repository.findWishById(1);
        assertNull(wish);
    }
}