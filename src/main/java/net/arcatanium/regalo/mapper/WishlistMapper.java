package net.arcatanium.regalo.mapper;

import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Mapper(uses = WishlistItemMapper.class, componentModel = "spring")
public interface WishlistMapper {
    WishlistItemMapper wishlistItemMapper = Mappers.getMapper(WishlistItemMapper.class);

    @Mapping(target = "id", source = "wishlistId")
    @Mapping(target = "wishlistItems", source = "wishlistItemEntityList")
    Wishlist entityToModel (WishlistEntity wishlistEntity);

    default WishlistEntity modelToEntity (Wishlist wishlist) {
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
