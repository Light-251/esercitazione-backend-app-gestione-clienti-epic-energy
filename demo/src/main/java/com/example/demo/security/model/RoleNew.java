package com.example.demo.security.model;

import org.springframework.security.core.GrantedAuthority;

public class RoleNew implements GrantedAuthority {

    private String authority;

    public RoleNew(String authority) {
        setAuthority(authority);
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
