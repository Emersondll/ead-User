package com.ead.authuser.builder;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class BuilderHelper {

   public UserEntity getUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setUserId(UUID.randomUUID());
        entity.setUsername("Emerson");
        entity.setEmail("emeailTest");
        entity.setFullName("Emerson Lima");
        entity.setUserStatus(UserStatus.ACTIVE);
        entity.setUserType(UserType.STUDENT);
        entity.setPhoneNumber("123");
        entity.setDocumentNumber("999999");
        entity.setPassword("PassWord");
        return entity;
    }

     public UserDto getUserDto() {
          UserDto dto = new UserDto();
          dto.setId(UUID.randomUUID());
          dto.setUsername("Emerson");
          dto.setEmail("emeailTest2");
          dto.setFullName("Emerson Lima2");
          dto.setPhoneNumber("123");
          dto.setDocumentNumber("999999");
          dto.setPassword("New PassWord");
          dto.setOldPassword("PassWord");
          return dto;
     }
}
