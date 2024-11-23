package net.arcatanium.regalo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Wishlist {
    private String id;
    private String name;
    private List<WishlistItem> wishlistItems;

}
