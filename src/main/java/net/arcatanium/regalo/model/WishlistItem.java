/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
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

    @Size(max=255)
    private String name;

    @Size(max=2048)
    private String description;

    @Size(max=2048)
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
