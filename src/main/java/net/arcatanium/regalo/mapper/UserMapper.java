package net.arcatanium.regalo.mapper;

import net.arcatanium.regalo.model.User;
import net.arcatanium.regalo.model.jpa.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = WishlistMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "wishlists", source = "wishlistEntityList")
    User entityToModel (UserEntity userEntity);

    @Mapping(target = "wishlistEntityList", source = "wishlists")
    UserEntity modelToEntity (User user);
}
