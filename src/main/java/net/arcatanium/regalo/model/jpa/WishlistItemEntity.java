package net.arcatanium.regalo.model.jpa;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = WishlistItemEntity.WISHLIST_ITEM_TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistItemEntity extends BaseEntity{
    public static final String WISHLIST_ITEM_TABLE_NAME = "WISHLIST_ITEM";

    @Id
    @Column(name = "WISHLIST_ITEM_ID")
    private String wishlistItemId;

    private String name;

    private String description;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WISHLIST_ID")
    private WishlistEntity wishlistEntity;
}
