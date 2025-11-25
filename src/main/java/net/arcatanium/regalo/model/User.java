package net.arcatanium.regalo.model;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import net.arcatanium.regalo.model.jpa.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class User {
    @Size(max=36)
    private String userId;

    @Size(max=255)
    private String userName;

    private List<Wishlist> wishlists;

    public static User convertFromEntity(UserEntity userEntity){
        return User.builder()
                .userId(userEntity.getUserId().toString())
                .userName(userEntity.getUserName())
                .wishlists(userEntity.getWishlistEntityList() != null ?
                        userEntity.getWishlistEntityList().stream()
                                .map(Wishlist::convertFromEntity)
                                .collect(Collectors.toList()) : List.of())
                .build();
    }
}
