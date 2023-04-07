package com.ead.authuser.repository;

import com.ead.authuser.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean findByUsername(final String username);
    boolean findByEmail(final String email);
}
