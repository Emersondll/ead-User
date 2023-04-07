package com.ead.authuser.service.impl;

import com.ead.authuser.model.User;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public User save(User user) {
        return null;
    }
}
