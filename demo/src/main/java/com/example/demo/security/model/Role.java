package com.example.demo.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.model.Permission.*;

@Deprecated
public enum Role {

    USER
            (
                    Set.of(
                            USER_READ
                    )
            ), ADMIN
            (
                    Set.of(
                            ADMIN_CREATE,
                            ADMIN_READ,
                            ADMIN_UPDATE,
                            ADMIN_DELETE
                    )
            );
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    //
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
