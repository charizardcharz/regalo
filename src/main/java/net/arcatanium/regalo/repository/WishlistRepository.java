package net.arcatanium.regalo.repository;

import net.arcatanium.regalo.model.jpa.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WishlistRepository extends JpaRepository<WishlistEntity, UUID> {
    Optional<WishlistEntity> findByWishlistId(UUID wishlistId);
}
