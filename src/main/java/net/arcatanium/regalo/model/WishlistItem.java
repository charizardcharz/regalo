package net.arcatanium.regalo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemEntity;

import java.util.UUID;

@Data
@Builder
public class WishlistItem {
    private Integer number;
    @JsonIgnore
    private UUID wishlistId;
    private String name;
    private String description;
    private String url;

    public static WishlistItem convertFromEntity(WishlistItemEntity wishlistItemEntity) {
        return WishlistItem.builder()
                .number(wishlistItemEntity.getSequenceNumber())
                .wishlistId(wishlistItemEntity.getWishlistEntity().getWishlistId())
                .name(wishlistItemEntity.getName())
                .description(wishlistItemEntity.getDescription())
                .url(wishlistItemEntity.getUrl())
                .build();
    }

    public static WishlistItemEntity convertToEntity(WishlistItem wishlistItem, WishlistEntity wishlistEntity) {
        return WishlistItemEntity.builder()
                .sequenceNumber(wishlistItem.getNumber())
                .wishlistEntity(wishlistEntity)
                .name(wishlistItem.getName())
                .description(wishlistItem.getDescription())
                .url(wishlistItem.getUrl())
                .build();
    }
}
