/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.repository;

import net.arcatanium.regalo.model.jpa.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WishlistRepository extends JpaRepository<WishlistEntity, UUID> {
    Optional<WishlistEntity> findByWishlistId(UUID wishlistId);
}
