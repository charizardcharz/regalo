package net.arcatanium.regalo.mapper;

import net.arcatanium.regalo.model.WishlistItem;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistItemMapper {
    @Mapping(target = "number", source = "sequenceNumber")
    @Mapping(target = "wishlistId", source = "wishlistEntity.wishlistId")
    WishlistItem entityToModel (WishlistItemEntity wishlistItemEntity);

    @Mapping(target = "sequenceNumber", source = "wishlistItem.number")
    @Mapping(target = "name", source = "wishlistItem.name")
    WishlistItemEntity modelToEntity (WishlistItem wishlistItem, WishlistEntity wishlistEntity);


}
