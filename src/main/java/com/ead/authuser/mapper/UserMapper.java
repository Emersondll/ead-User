package com.ead.authuser.mapper;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default Page<UserDto> converterPageEntityToPageDto(final Page<UserEntity> userEntities) {
        return userEntities.map(this::converterEntityToDto);
    }

    @Mapping(target = "oldPassword", ignore = true)
    @Mapping(target = "id", source = "userId")
    UserDto converterEntityToDto(final UserEntity userEntities);
}
