package net.arcatanium.regalo.service;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.WishlistItem;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemEntity;
import net.arcatanium.regalo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
                .map(wishlistEntity -> Wishlist.builder()
                        .id(wishlistEntity.getWishlistId())
                        .name(wishlistEntity.getName())
                        .wishlistItems(wishlistEntity.getWishlistItemEntityList().stream()
                                .map(this::convertWishlistItemFromEntity)
                                .toList())
                        .build())
                .toList();
    }

    public Optional<Wishlist> getWishlistById(String id) {
        log.debug("Searching for wishlist with id {}", id);
        Optional<WishlistEntity> wishlistEntityOptional = wishlistRepository.findByWishlistId(id);

        if (wishlistEntityOptional.isPresent()){
            WishlistEntity wishlistEntity = wishlistEntityOptional.get();
            List<WishlistItem> wishlistItems = wishlistEntity.getWishlistItemEntityList().stream()
                    .map(this::convertWishlistItemFromEntity)
                    .toList();

            return Optional.of(Wishlist.builder()
                    .id(wishlistEntity.getWishlistId())
                    .name(wishlistEntity.getName())
                    .wishlistItems(wishlistItems)
                    .build());
        } else {
            return Optional.empty();
        }
    }

    public WishlistEntity saveWishlist(Wishlist wishlist) {
        Optional<WishlistEntity> wishlistEntityOptional = wishlistRepository.findByWishlistId(wishlist.getId());
        WishlistEntity wishlistEntity;

        if (wishlistEntityOptional.isEmpty()) {
            log.debug("Creating new wishlist");
            wishlistEntity = WishlistEntity.builder()
                    .wishlistId(UUID.randomUUID().toString())
                    .name(wishlist.getName())
                    .build();

        } else {
            log.debug("Updating wishlist with id {}", wishlist.getId());
            wishlistEntity = wishlistEntityOptional.get();
        }

        List<WishlistItemEntity> wishlistItemEntityList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(wishlistEntity.getWishlistItemEntityList())) {
        }

        wishlistEntity.setWishlistItemEntityList(wishlistItemEntityList);

        if (CollectionUtils.isEmpty(wishlist.getWishlistItems())){
            wishlistEntity.setWishlistItemEntityList(List.of());
        } else {
            wishlistEntity.setWishlistItemEntityList(wishlist.getWishlistItems().stream()
                    .map(wishlistItem -> convertWishlistItemToEntity(wishlistEntity, wishlistItem))
                    .toList());
        }

        return wishlistRepository.save(wishlistEntity);
    }

    private WishlistItem convertWishlistItemFromEntity(WishlistItemEntity wishlistItemEntity) {
        return WishlistItem.builder()
                        .id(wishlistItemEntity.getWishlistItemId())
                        .name(wishlistItemEntity.getName())
                        .description(wishlistItemEntity.getDescription())
                        .url(wishlistItemEntity.getUrl())
                        .build();
    }

    private WishlistItemEntity convertWishlistItemToEntity(WishlistEntity wishlistEntity, WishlistItem wishlistItem) {
        return WishlistItemEntity.builder()
                .wishlistItemId(StringUtils.hasLength(wishlistItem.getId()) ? wishlistItem.getId() : UUID.randomUUID().toString())
                .name(wishlistItem.getName())
                .description(wishlistItem.getDescription())
                .url(wishlistItem.getUrl())
                .wishlistEntity(wishlistEntity)
                .build();
    }
}
