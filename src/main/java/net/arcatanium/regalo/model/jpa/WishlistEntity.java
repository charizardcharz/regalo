/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.model.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = WishlistEntity.WISHLIST_TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistEntity extends BaseEntity {
    public static final String WISHLIST_TABLE_NAME = "WISHLIST";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "WISHLIST_ID")
    private UUID wishlistId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "wishlistEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<WishlistItemEntity> wishlistItemEntityList;

}
