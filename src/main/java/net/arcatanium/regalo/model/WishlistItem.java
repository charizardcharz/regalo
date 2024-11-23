package net.arcatanium.regalo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistItem {
    @JsonIgnore
    private String id;
    private String name;
    private String description;
    private String url;
}
