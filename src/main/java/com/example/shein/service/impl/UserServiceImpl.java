package com.example.shein.service.impl;

import com.example.shein.model.binding.UserRegisterBindingModel;
import com.example.shein.model.entity.UserEntity;
import com.example.shein.model.entity.UserRoleEntity;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.UserRegisterServiceModel;
import com.example.shein.repository.UserRepository;
import com.example.shein.repository.UserRoleRepository;
import com.example.shein.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final SheinUserServiceImpl userService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, SheinUserServiceImpl userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
    }

    @Override
    public void initUsersAndRoles() {

        initRoles();
        initUsers();

    }

    private void initRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity();
            admin.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity user = new UserRoleEntity();
            user.setRole(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(admin, user));

        }

    }


    private void initUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin.setFirstName("Stanimir").
                    setLastName("Kovachev").
                    setEmail("stanimirkovachev@gmail.com").
                    setPassword(passwordEncoder.encode("test")).
                    setUsername("admin");

            admin.setRoles(Set.of(adminRole, userRole));

            userRepository.save(admin);
        }

    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel serviceModel) {
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
        UserEntity newUser = new UserEntity();

        newUser.
                setUsername(serviceModel.getUsername()).
                setFirstName(serviceModel.getFirstName()).
                setLastName(serviceModel.getLastName()).
                setEmail(serviceModel.getEmail()).
                setPassword(passwordEncoder.encode(serviceModel.getPassword())).
                setRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);

        UserDetails principal = userService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, newUser.getPassword(), principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
