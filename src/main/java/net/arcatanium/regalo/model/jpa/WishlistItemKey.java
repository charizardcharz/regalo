package net.arcatanium.regalo.model.jpa;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class WishlistItemKey implements Serializable {
    public Integer sequenceNumber;
    public WishlistEntity wishlistEntity;
}
