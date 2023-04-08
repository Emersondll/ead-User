package com.ead.authuser.repository;

import com.ead.authuser.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByUsername(final String username);

    boolean existsByEmail(final String email);
}
