package com.ead.authuser.controller;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import com.ead.authuser.specification.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    public static final String USER_NOT_FOUND = "User Not Found";
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserModel>> findAll(SpecificationTemplate.UserSpec spec,
                                                   @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
                                                   Pageable pageable, @RequestParam(required = false) UUID courseId) {

        Page<UserModel> userModelPage = null;
        if (Objects.nonNull(courseId)) {
            userModelPage = service.findAll(SpecificationTemplate.userCourseId(courseId).and(spec), pageable);
        } else {
            userModelPage = service.findAll(spec, pageable);
        }

        if (!userModelPage.isEmpty()) {
            for (UserModel user : userModelPage.toList()) {

                user.add(linkTo(methodOn(UserController.class).findById(user.getUserId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> findById(@PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> response = service.findById(userId);
        return response.<ResponseEntity<Object>>map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND));


    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "userId") UUID userId) {

        Optional<UserModel> response = service.findById(userId);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        service.delete(response.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Deleted");

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateById(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody @Validated(UserDto.UserView.UserPut.class)
                                             @JsonView(UserDto.UserView.UserPut.class) UserDto userDto) {
        Optional<UserModel> response = service.findById(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        UserModel userModel = response.get();
        userModel.setCpf(userDto.getCpf());
        userModel.setFullName(userDto.getFullName());
        userModel.setPhoneNumber(userDto.getPhoneNumber());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        service.save(userModel);

        return ResponseEntity.status(HttpStatus.OK).body(userModel);

    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
                                                 @RequestBody @Validated(UserDto.UserView.PasswordPut.class)
                                                 @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto) {
        Optional<UserModel> response = service.findById(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        if (!response.get().getPassword().equals(userDto.getOldPassword())) { // TODO Melhorar validação
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR: Mismatched Password");
        }
        UserModel userModel = response.get();
        userModel.setPassword(userDto.getPassword());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        service.save(userModel);

        return ResponseEntity.status(HttpStatus.OK).body("Password Updated Successfully");

    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID userId,
                                              @RequestBody @Validated(UserDto.UserView.ImagePut.class)
                                              @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto) {
        Optional<UserModel> response = service.findById(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }

        UserModel userModel = response.get();
        userModel.setImageUrl(userDto.getImageUrl());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        service.save(userModel);

        return ResponseEntity.status(HttpStatus.OK).body(userModel);

    }
}
