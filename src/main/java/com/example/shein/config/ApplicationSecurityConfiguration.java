package com.example.shein.config;

import com.example.shein.service.SheinUserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private SheinUserServiceImpl sheinUserService;
    private PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(SheinUserServiceImpl sheinUserService, PasswordEncoder passwordEncoder) {
        this.sheinUserService = sheinUserService;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sheinUserService).passwordEncoder(passwordEncoder);
    }
}
