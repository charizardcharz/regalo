/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.model;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class Wishlist {
    @Size(max=36)
    private String id;

    @Size(max=255)
    private String name;

    private List<WishlistItem> wishlistItems;
}
