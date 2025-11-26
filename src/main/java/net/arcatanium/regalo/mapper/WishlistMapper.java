package net.arcatanium.regalo.mapper;

import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Mapper(uses = WishlistItemMapper.class, componentModel = "spring")
public abstract class WishlistMapper {
    @Autowired
    protected WishlistItemMapper wishlistItemMapper;

    @Mapping(target = "id", source = "wishlistId")
    @Mapping(target = "wishlistItems", source = "wishlistItemEntityList")
    public abstract Wishlist entityToModel (WishlistEntity wishlistEntity);

    public WishlistEntity modelToEntity (Wishlist wishlist) {
        WishlistEntity wishlistEntity = WishlistEntity.builder()
                .wishlistId(wishlist.getId() != null ?  UUID.fromString(wishlist.getId()) : null)
                .name(wishlist.getName())
                .build();

        if (CollectionUtils.isEmpty(wishlist.getWishlistItems())) {
            wishlistEntity.setWishlistItemEntityList(List.of());
        } else {
            wishlistEntity.setWishlistItemEntityList(wishlist.getWishlistItems().stream()
                    .map(wishlistItem -> wishlistItemMapper.modelToEntity(wishlistItem, wishlistEntity))
                    .toList());
        }

        return wishlistEntity;
    }

}
