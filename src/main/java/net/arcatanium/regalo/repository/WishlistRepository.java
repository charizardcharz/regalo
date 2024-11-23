package net.arcatanium.regalo.repository;

import net.arcatanium.regalo.model.jpa.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishlistEntity, String> {
    Optional<WishlistEntity> findByWishlistId(String wishlistId);
}
