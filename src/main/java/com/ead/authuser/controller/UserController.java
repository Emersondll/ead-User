package com.ead.authuser.controller;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.service.UserService;
import com.ead.authuser.specification.SpecificationTemplate;
import com.ead.authuser.utils.GeneralMessage;
import com.ead.authuser.utils.JsonMessageProperties;
import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserEntity>> findAll(SpecificationTemplate.UserSpec spec,
                                                    @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
                                                    Pageable pageable) {

        Page<UserEntity> userEntities = service.findAll(spec, pageable);

        if (!userEntities.isEmpty()) {
            for (UserEntity user : userEntities.toList()) {

                user.add(linkTo(methodOn(UserController.class).findById(user.getUserId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(userEntities);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> findById(@PathVariable(value = "userId") UUID userId) {
        Optional<UserEntity> response = service.findById(userId);
        return response.<ResponseEntity<Object>>map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(GeneralMessage.USER_NOT_FOUND));


    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "userId") UUID userId) throws NotFoundException {

        service.deleteById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(GeneralMessage.USER_DELETED_SUCCESS);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateById(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody @Validated(UserDto.UserView.UserPut.class)
                                             @JsonView(UserDto.UserView.UserPut.class) UserDto userDto) throws NotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(service.updateById(userId, userDto));

    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
                                                 @RequestBody @Validated(UserDto.UserView.PasswordPut.class)
                                                 @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto) throws NotFoundException {
        service.updatePassword(userId, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(new JsonMessageProperties(GeneralMessage.UPDATE_PASSWORD_SUCCESS, HttpStatus.OK));

    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID userId,
                                              @RequestBody @Validated(UserDto.UserView.ImagePut.class)
                                              @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto) {
        Optional<UserEntity> response = service.findById(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GeneralMessage.USER_NOT_FOUND);
        }

        UserEntity userEntity = response.get();
        userEntity.setImageUrl(userDto.getImageUrl());
        userEntity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        service.save(userEntity);

        return ResponseEntity.status(HttpStatus.OK).body(userEntity);

    }
}
