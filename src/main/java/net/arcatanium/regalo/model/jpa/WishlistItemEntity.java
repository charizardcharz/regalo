/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = WishlistItemEntity.WISHLIST_ITEM_TABLE_NAME)
@IdClass(WishlistItemKey.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistItemEntity extends BaseEntity{
    public static final String WISHLIST_ITEM_TABLE_NAME = "WISHLIST_ITEM";

    @Id
    @Column(name = "SEQUENCE_NUM")
    private Integer sequenceNumber;

    @Id
    @ManyToOne
    @JoinColumn(name = "WISHLIST_ID")
    @JsonIgnore
    @ToString.Exclude
    private WishlistEntity wishlistEntity;

    private String name;

    @Column(name = "DESCRIPTION", length = 2048)
    private String description;

    @Column(name = "URL", length = 2048)
    private String url;


}
