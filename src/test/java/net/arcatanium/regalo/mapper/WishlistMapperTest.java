package net.arcatanium.regalo.mapper;

import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.WishlistItem;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class WishlistMapperTest {
    @Autowired
    WishlistMapper wishlistMapper;
    
    @Test
    void entityToModelTest () {
        WishlistEntity wishlistEntity = WishlistEntity.builder()
                .wishlistId(UUID.fromString("ee7f6cd5-2847-41f4-b26e-cf4140c2154e"))
                .name("Test List")
                .build();


        WishlistItemEntity wishlistItemEntity = WishlistItemEntity.builder()
                .sequenceNumber(1)
                .name("Test Item")
                .url("test URL")
                .description("test description")
                .wishlistEntity(wishlistEntity)
                .build();

        wishlistEntity.setWishlistItemEntityList(List.of(wishlistItemEntity));

        Wishlist wishlist = wishlistMapper.entityToModel(wishlistEntity);

        assertEquals(wishlistEntity.getWishlistId().toString(), wishlist.getId());
        assertEquals(wishlistEntity.getName(), wishlist.getName());

        assertFalse(CollectionUtils.isEmpty(wishlist.getWishlistItems()));

        WishlistItem wishlistItem = wishlist.getWishlistItems().getFirst();

        assertEquals(wishlistItemEntity.getSequenceNumber(), wishlistItem.getNumber());
        assertEquals(wishlistItemEntity.getName(), wishlistItem.getName());
    }

    @Test
    void modelToEntityTest () {
        Wishlist wishlist = Wishlist.builder()
                .id("ee7f6cd5-2847-41f4-b26e-cf4140c2154e")
                .name("Test List")
                .build();

        WishlistItem wishlistItem = WishlistItem.builder()
                .number(1)
                .name("Test Item")
                .url("Test URL")
                .description("Test Description")
                .wishlistId(UUID.fromString(wishlist.getId()))
                .build();

        wishlist.setWishlistItems(List.of(wishlistItem));

        WishlistEntity wishlistEntity = wishlistMapper.modelToEntity(wishlist);

        assertEquals(wishlist.getId(), wishlistEntity.getWishlistId().toString());
        assertEquals(wishlist.getName(), wishlistEntity.getName());

        assertFalse(CollectionUtils.isEmpty(wishlistEntity.getWishlistItemEntityList()));

        WishlistItemEntity wishlistItemEntity = wishlistEntity.getWishlistItemEntityList().getFirst();

        assertEquals(wishlistItem.getNumber(), wishlistItemEntity.getSequenceNumber());
        assertEquals(wishlistItem.getName(), wishlistItemEntity.getName());
    }
}
