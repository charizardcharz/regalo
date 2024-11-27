package net.arcatanium.regalo.service;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.repository.WishlistRepository;
import net.arcatanium.regalo.util.RegaloUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        UUID wishlistId = RegaloUtils.isValidUUID(id) ? UUID.fromString(id) : null;

        if (wishlistId != null){
            return wishlistRepository.findByWishlistId(wishlistId).map(Wishlist::convertFromEntity);
        } else{
            return Optional.empty();
        }
    }

    public Optional<WishlistEntity> saveWishlist(Wishlist wishlist) {
        if (StringUtils.hasLength(wishlist.getId()) && RegaloUtils.isValidUUID(wishlist.getId())) {
            if(wishlistRepository.findByWishlistId(UUID.fromString(wishlist.getId())).isEmpty()) {
                log.warn("Wishlist with ID {} does not yet exist. Generating new ID.", wishlist.getId());
                wishlist.setId(null);
            } else {
                log.debug("Updating wishlist with id {}", wishlist.getId());
            }
        } else {
            log.debug("Saving new wishlist");
        }

        return Optional.of(wishlistRepository.save(Wishlist.convertToEntity(wishlist)));
    }

    /** Delete wishlist by ID
     * @param id Wishlist UUID as String
     * @return true if wishlist existed, false if wishlist did not exist
     */
    public boolean deleteWishlistById(String id) {
        UUID wishlistId = RegaloUtils.isValidUUID(id) ? UUID.fromString(id) : null;

        if (wishlistId != null && wishlistRepository.existsById(wishlistId)) {
            log.debug("Deleting wishlist with id {}", id);
            wishlistRepository.deleteById(wishlistId);
            return true;
        } else {
            return false;
        }
    }

    public WishlistEntity createNewWishlist() {
        log.debug("Creating new empty wishlist");
        WishlistEntity wishlistEntity = WishlistEntity.builder()
                .name("New Wishlist")
                .build();
        return wishlistRepository.save(wishlistEntity);
    }


}
