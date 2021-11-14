package com.example.shein.service.impl;

import com.example.shein.model.entity.UserEntity;
import com.example.shein.model.entity.UserRoleEntity;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.repository.UserRepository;
import com.example.shein.repository.UserRoleRepository;
import com.example.shein.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initUsersAndRoles() {

        initRoles();
        initUsers();

    }

    private void initRoles(){

        if(userRoleRepository.count() == 0){
            UserRoleEntity admin  = new UserRoleEntity();
            admin.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity user = new UserRoleEntity();
            user.setRole(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(admin,user));

        }

    }


    private void initUsers() {

        if(userRepository.count() == 0){

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin.setFirstName("Stanimir").
                    setLastName("Kovachev").
                    setEmail("stanimirkovachev@gmail.com").
                    setPassword(passwordEncoder.encode("test")).
                    setUsername("admin");

            admin.setRoles(Set.of(adminRole,userRole));

            userRepository.save(admin);
        }

    }

}
