package com.example.demo.security.model;

@Deprecated
public enum Permission {
    ADMIN_CREATE("ADMIN_CREATE"),
    ADMIN_READ("ADMIN_READ"),
    ADMIN_UPDATE("ADMIN_UPDATE"),
    ADMIN_DELETE("ADMIN_DELETE"),

    USER_READ("USER_READ");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
