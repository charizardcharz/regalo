package net.arcatanium.regalo.model;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    @Size(max=36)
    private String userId;

    @Size(max=255)
    private String userName;

    private List<Wishlist> wishlists;
}
