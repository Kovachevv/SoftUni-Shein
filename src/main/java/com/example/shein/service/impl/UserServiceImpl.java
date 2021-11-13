package com.example.shein.service.impl;

import com.example.shein.model.entity.UserEntity;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void initUsers() {

        if(userRepository.count() != 0){
            return;

        }
        UserEntity user = new UserEntity();
        user.setFirstName("Stanimir").
                setLastName("Kovachev").
                setEmail("stanimirkovachev@gmail.com").
                setPassword(passwordEncoder.encode("test")).
                setUsername("admin");

        //TODO add roles.

    }
}
