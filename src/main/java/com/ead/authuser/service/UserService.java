package com.ead.authuser.service;

import com.ead.authuser.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User save(final User user);

    List<User> findAll();

    Optional<User> findById(final UUID id);

    void delete(final User response);
}
