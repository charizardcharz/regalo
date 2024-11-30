/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.service;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemKey;
import net.arcatanium.regalo.repository.WishlistItemRepository;
import net.arcatanium.regalo.repository.WishlistRepository;
import net.arcatanium.regalo.util.RegaloUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, WishlistItemRepository wishlistItemRepository) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistItemRepository = wishlistItemRepository;
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
            if(!wishlistRepository.existsById(UUID.fromString(wishlist.getId()))) {
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

    public Optional<WishlistItemEntity> createNewWishlistItem(String wishlistId) {
        log.debug("Creating new empty wishlist item");
        Optional<WishlistEntity> wishlistEntityOptional = RegaloUtils.isValidUUID(wishlistId) ?
                wishlistRepository.findByWishlistId(UUID.fromString(wishlistId)) : Optional.empty();
        if (wishlistEntityOptional.isPresent()){
            WishlistEntity wishlistEntity = wishlistEntityOptional.get();
            int wishlistItemCount = CollectionUtils.isEmpty(wishlistEntity.getWishlistItemEntityList()) ? 0 : wishlistEntity.getWishlistItemEntityList().size();

            WishlistItemEntity wishlistItemEntity = WishlistItemEntity.builder()
                    .sequenceNumber(wishlistItemCount + 1)
                    .wishlistEntity(wishlistEntityOptional.get())
                    .build();

            return Optional.of(wishlistItemRepository.save(wishlistItemEntity));
        } else {
            return Optional.empty();
        }
    }

    public void deleteWishlistItemByKey(String wishlistId, Integer sequenceNumber) {
        Optional<WishlistEntity> wishlistEntityOptional = RegaloUtils.isValidUUID(wishlistId) ?
                wishlistRepository.findByWishlistId(UUID.fromString(wishlistId)) : Optional.empty();


        if (wishlistEntityOptional.isPresent()) {
            WishlistItemKey wishlistItemKey = new WishlistItemKey(sequenceNumber, wishlistEntityOptional.get());

            wishlistItemRepository.deleteById(wishlistItemKey);
        }
    }
}
