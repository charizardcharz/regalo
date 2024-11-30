/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.repository;

import net.arcatanium.regalo.model.jpa.WishlistItemEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistItemRepository extends JpaRepository<WishlistItemEntity, WishlistItemKey> {
    @Query(value = "SELECT * FROM " + WishlistItemEntity.WISHLIST_ITEM_TABLE_NAME + " WHERE WISHLIST_ID = :wishlistId", nativeQuery = true)
    List<WishlistItemEntity> findByWishlistId(Integer wishlistId);
}