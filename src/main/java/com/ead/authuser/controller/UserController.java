package com.ead.authuser.controller;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> findById(@PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> response = service.findById(userId);
        return response.<ResponseEntity<Object>>map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found"));


    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "userId") UUID userId) {

        Optional<UserModel> response = service.findById(userId);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        UserModel userModel = response.get();
        userModel.setImageUrl(userDto.getImageUrl());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        service.save(userModel);

        return ResponseEntity.status(HttpStatus.OK).body(userModel);

    }
}
