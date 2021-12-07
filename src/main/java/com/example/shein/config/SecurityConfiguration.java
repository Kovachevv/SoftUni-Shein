package com.example.shein.config;

import com.example.shein.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private SheinMethodSecurityExpressionHandler sheinMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return sheinMethodSecurityExpressionHandler;
    }

    @Bean
    public SheinMethodSecurityExpressionHandler createExpressionHandler(BrandService brandService) {
        return new SheinMethodSecurityExpressionHandler(brandService);
    }

}