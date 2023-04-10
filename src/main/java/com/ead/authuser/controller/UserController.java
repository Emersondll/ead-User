package com.ead.authuser.controller;

import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/id")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {

        Optional<UserModel> response = service.findById(id);
        return response.<ResponseEntity<Object>>map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found"));


    }

    @DeleteMapping("/id")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id) {

        Optional<UserModel> response = service.findById(id);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        service.delete(response.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Deleted");

    }
}
