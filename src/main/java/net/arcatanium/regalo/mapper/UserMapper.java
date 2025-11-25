package net.arcatanium.regalo.mapper;

import net.arcatanium.regalo.model.User;
import net.arcatanium.regalo.model.jpa.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User entityToModel (UserEntity userEntity);
    UserEntity modelToEntity (User user);
}
