package net.arcatanium.regalo.model.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = WishlistEntity.WISHLIST_TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistEntity extends BaseEntity{
    public static final String WISHLIST_TABLE_NAME = "WISHLIST";

    @Id
    @Column(name = "WISHLIST_ID")
    private String wishlistId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "wishlistEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WishlistItemEntity> wishlistItemEntityList;

}
