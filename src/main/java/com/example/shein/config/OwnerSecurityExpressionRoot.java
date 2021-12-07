package com.example.shein.config;

import com.example.shein.service.BrandService;
;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class OwnerSecurityExpressionRoot extends SecurityExpressionRoot implements
        MethodSecurityExpressionOperations {

    private BrandService brandService;
    private Object filterObject;
    private Object returnObject;

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public OwnerSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isAdmin(Long id) {
        String userName = currentUserName();
        if (userName != null) {
            return brandService.isAdmin(userName);
        }
        return false;
    }


    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    public String currentUserName() {
        Authentication auth = getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails)auth.getPrincipal()).getUsername();
        }
        return null;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}