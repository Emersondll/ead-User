package com.ead.authuser.service.impl;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;
import com.ead.authuser.utils.GeneralMessage;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserEntity save(final UserEntity user) {
        return repository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(final UUID id) {
        return repository.findById(id);
    }

    @Override
    public void delete(final UserEntity user) {
        repository.delete(user);
    }

    @Override
    public boolean existsByUsername(final String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Page<UserEntity> findAll(final Specification<UserEntity> spec, final Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public void deleteById(final UUID userId) throws NotFoundException {

        Optional<UserEntity> response = findById(userId);

        if (response.isEmpty()) {

            throw new NotFoundException(GeneralMessage.USER_NOT_FOUND);
        }
        delete(response.get());

    }

    @Override
    public Optional<UserEntity> updateById(final UUID userId, final UserDto userDto) throws NotFoundException {
        Optional<UserEntity> response = findById(userId);
        if (response.isEmpty()) {
            throw new NotFoundException(GeneralMessage.USER_NOT_FOUND);
        }
        UserEntity userEntity = response.get();
        userEntity.setCpf(userDto.getCpf());
        userEntity.setFullName(userDto.getFullName());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        save(userEntity);
        return Optional.of(userEntity);
    }

    @Override
    public void updatePassword(UUID userId, UserDto userDto) throws NotFoundException {

        Optional<UserEntity> response = findById(userId);
        if (response.isEmpty()) {
            throw new NotFoundException(GeneralMessage.USER_NOT_FOUND);
        }
        if (!response.get().getPassword().equals(userDto.getOldPassword())) {
            throw new SecurityException(GeneralMessage.UPDATE_PASSWORD_ERROR);
        }
        if (response.get().getPassword().equals(userDto.getPassword())) {
            throw new SecurityException(GeneralMessage.UPDATE_PASSWORD_ERROR_WITH_SAME_VALUES);
        }
        UserEntity userEntity = response.get();
        userEntity.setPassword(userDto.getPassword());
        userEntity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        save(userEntity);
    }
}
