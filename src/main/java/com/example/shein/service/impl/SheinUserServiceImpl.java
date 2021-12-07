package com.example.shein.service.impl;

import com.example.shein.model.entity.UserEntity;
import com.example.shein.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SheinUserServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;


    public SheinUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity user){

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).collect(Collectors.toList());

        return new SheinUser(user.getUsername(),user.getPassword(),authorities);
    }
}
