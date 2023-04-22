package com.ead.authuser.service;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserEntity save(final UserEntity user);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(final UUID id);

    void delete(final UserEntity response);

    boolean existsByUsername(final String username);

    boolean existsByEmail(final String email);

    Page<UserEntity> findAll(final Specification<UserEntity> spec, final Pageable pageable);

    void deleteById(final UUID userId) throws NotFoundException;

    Optional<UserEntity> updateById(final UUID userId, final UserDto userDto) throws NotFoundException;

    void updatePassword(final UUID userId, final UserDto userDto) throws NotFoundException;

    Optional<UserEntity> updateImage(final UUID userId,final UserDto userDto) throws NotFoundException;

    UserDto findUserById(final UUID userId) throws NotFoundException;
}
