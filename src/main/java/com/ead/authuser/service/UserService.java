package com.ead.authuser.service;

import com.ead.authuser.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserModel save(final UserModel user);

    List<UserModel> findAll();

    Optional<UserModel> findById(final UUID id);

    void delete(final UserModel response);

    boolean existsByUsername(final String username);

    boolean existsByEmail(final String email);

    Page<UserModel> findAll(final Specification<UserModel> spec, final Pageable pageable);
}
