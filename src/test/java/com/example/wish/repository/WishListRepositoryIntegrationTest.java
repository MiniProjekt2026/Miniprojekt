package com.example.wish.repository;

import com.example.wish.model.WishList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password="
})
@ActiveProfiles("test")
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class WishListRepositoryIntegrationTest {

    @Autowired
    private WishListRepository repository;

    @Test
    void getAllWishLists_returnsDataWithWishesAndTags() {
        List<WishList> lists = repository.getAllWishLists();

        assertFalse(lists.isEmpty());

        WishList wl = lists.get(0);

        assertEquals("Birthday", wl.getName());
        assertNotNull(wl.getWishes());
        assertFalse(wl.getWishes().isEmpty());

        assertEquals("Laptop", wl.getWishes().get(0).getName());
        assertEquals("Electronics", wl.getWishes().get(0).getTags().get(0));
    }

    @Test
    void findWishListByUserId_returnsCorrectLists() {
        List<WishList> lists = repository.findWishListsByUserId(1);

        assertEquals(1, lists.size());
        assertEquals("Birthday", lists.get(0).getName());
    }

    @Test
    void addWishList_and_findByName() {
        WishList wl = new WishList();
        wl.setName("Christmas");
        wl.setUserId(1);

        repository.addWishList(wl);

        Integer id = repository.findWishListIdByName("Christmas");

        assertNotNull(id);
    }

    @Test
    void deleteWishList_removesData() {
        boolean deleted = repository.deleteWishList(1);

        assertTrue(deleted);

        WishList wl = repository.findWishListById(1);
        assertNull(wl);
    }
}
