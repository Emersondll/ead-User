package com.ead.authuser.controller;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {
        if (service.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
        }

        if (service.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setUserStatus(UserStatus.ACTIVE);
        userEntity.setUserType(UserType.STUDENT);
        userEntity.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userEntity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        service.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);


    }
}
