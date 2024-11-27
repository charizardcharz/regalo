package net.arcatanium.regalo.model;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Wishlist {
    @Size(max=36)
    private String id;

    @Size(max=255)
    private String name;

    private List<WishlistItem> wishlistItems;

    public static Wishlist convertFromEntity(WishlistEntity wishlistEntity) {
        return Wishlist.builder()
                .id(wishlistEntity.getWishlistId().toString())
                .name(wishlistEntity.getName())
                .wishlistItems(wishlistEntity.getWishlistItemEntityList() != null ?
                        wishlistEntity.getWishlistItemEntityList().stream()
                                .map(WishlistItem::convertFromEntity)
                                .toList() :
                        List.of())
                .build();
    }

    public static WishlistEntity convertToEntity(Wishlist wishlist) {
        WishlistEntity wishlistEntity = WishlistEntity.builder()
                .wishlistId(wishlist.getId() != null ?  UUID.fromString(wishlist.getId()) : null)
                .name(wishlist.getName())
                .build();

        if (CollectionUtils.isEmpty(wishlist.getWishlistItems())) {
            wishlistEntity.setWishlistItemEntityList(List.of());
        } else {
            wishlistEntity.setWishlistItemEntityList(wishlist.getWishlistItems().stream()
                    .map(wishlistItem -> WishlistItem.convertToEntity(wishlistItem, wishlistEntity))
                    .toList());
        }

        return wishlistEntity;
    }
}
