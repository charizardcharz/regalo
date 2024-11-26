package net.arcatanium.regalo.service;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getAllWishlists() {
        List<WishlistEntity> wishlistEntityList = wishlistRepository.findAll();

        return wishlistEntityList.stream()
                .map(Wishlist::convertFromEntity)
                .toList();
    }

    public Optional<Wishlist> getWishlistById(String id) {
        log.debug("Searching for wishlist with id {}", id);
        Optional<WishlistEntity> wishlistEntityOptional = wishlistRepository.findByWishlistId(UUID.fromString(id));

        return wishlistEntityOptional.map(Wishlist::convertFromEntity);
    }

    public WishlistEntity saveWishlist(Wishlist wishlist) {
        return wishlistRepository.save(Wishlist.convertToEntity(wishlist));
    }

    /** Delete wishlist by ID
     * @param id Wishlist UUID as String
     * @return true if wishlist existed, false if wishlist did not exist
     */
    public boolean deleteWishlistById(String id) {
        UUID wishlistId = UUID.fromString(id);

        if (wishlistRepository.existsById(wishlistId)) {
            wishlistRepository.deleteById(wishlistId);
            return true;
        } else {
            return false;
        }

    }
}
