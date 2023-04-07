package com.ead.authuser.service;

import com.ead.authuser.model.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserModel save(final UserModel user);

    List<UserModel> findAll();

    Optional<UserModel> findById(final UUID id);

    void delete(final UserModel response);

    boolean findByUsername(final String username);

    boolean findByEmail(final String email);
}
