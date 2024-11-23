package net.arcatanium.regalo.repository;

import net.arcatanium.regalo.model.jpa.WishlistItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistItemRepository extends JpaRepository<WishlistItemEntity, String> {
    @Query(value = "SELECT * FROM " + WishlistItemEntity.WISHLIST_ITEM_TABLE_NAME + " WHERE WISHLIST_ID = :wishlistId", nativeQuery = true)
    List<WishlistItemEntity> findByWishlistId(String wishlistId);
}