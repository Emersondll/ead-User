package com.ead.authuser.service.impl;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.mapper.UserMapper;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;
import com.ead.authuser.specification.SpecificationTemplate;
import com.ead.authuser.utils.GeneralMessage;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
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


    private final UserRepository repository;
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl( UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(final UserEntity user) {
        repository.save(user);
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

        findById(userId)
                .map(userEntity -> {
                    delete(userEntity);
                    return userEntity;
                })
                .orElseThrow(() -> new NotFoundException(GeneralMessage.USER_NOT_FOUND));

    }

    @Override
    public UserDto updateById(final UUID userId, final UserDto userDto) throws NotFoundException {
        UserEntity entity = findById(userId)
                .map(userEntity -> {
                    userEntity.setCpf(userDto.getCpf());
                    userEntity.setFullName(userDto.getFullName());
                    userEntity.setPhoneNumber(userDto.getPhoneNumber());
                    userEntity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
                    save(userEntity);
                    return userEntity;
                })
                .orElseThrow(() -> new NotFoundException(GeneralMessage.USER_NOT_FOUND));

        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public void updatePassword(UUID userId, UserDto userDto) throws NotFoundException {

        Optional<UserEntity> entity = findById(userId);
        validatedPasswordPolices(userDto, entity);
        entity.get().setPassword(userDto.getPassword());
        entity.get().setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        save(entity.get());
    }

    private static void validatedPasswordPolices(UserDto userDto, Optional<UserEntity> entity) throws NotFoundException {
        if (entity.isEmpty()) {
            throw new NotFoundException(GeneralMessage.USER_NOT_FOUND);
        }
        if (!entity.get().getPassword().equals(userDto.getOldPassword())) {
            throw new SecurityException(GeneralMessage.UPDATE_PASSWORD_ERROR);
        }
        if (entity.get().getPassword().equals(userDto.getPassword())) {
            throw new SecurityException(GeneralMessage.UPDATE_PASSWORD_ERROR_WITH_SAME_VALUES);
        }
    }

    @Override
    public UserDto updateImage(final UUID userId, final UserDto userDto) throws NotFoundException {
        UserEntity entity = findById(userId)
                .map(userEntity -> {
                    userEntity.setImageUrl(userDto.getImageUrl());
                    userEntity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
                    save(userEntity);
                    return userEntity;
                })
                .orElseThrow(() -> new NotFoundException(GeneralMessage.USER_NOT_FOUND));

        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;

    }

    @Override
    public UserDto findUserById(final UUID userId) throws NotFoundException {
        return findById(userId)
                .map(userEntity -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(userEntity, dto);
                    return dto;
                })
                .orElseThrow(() -> new NotFoundException(GeneralMessage.USER_NOT_FOUND));

    }

    @Override
    public Page<UserDto> findAllUsers(SpecificationTemplate.UserSpec spec, Pageable pageable) throws NotFoundException {
        Page<UserEntity> userEntities = findAll(spec, pageable);
        if(userEntities.isEmpty()){
            throw new NotFoundException(GeneralMessage.USERS_NOT_FOUND);
        }

        return UserMapper.INSTANCE.converterPageEntityToPageDto(userEntities);


    }
}
