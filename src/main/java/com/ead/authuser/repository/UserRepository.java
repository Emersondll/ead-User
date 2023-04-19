package com.ead.authuser.repository;

import com.ead.authuser.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    boolean existsByUsername(final String username);

    boolean existsByEmail(final String email);
}
