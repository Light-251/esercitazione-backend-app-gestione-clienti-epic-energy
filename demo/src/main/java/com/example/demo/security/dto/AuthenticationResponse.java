package com.example.demo.security.dto;

import io.jsonwebtoken.lang.Assert;

public class AuthenticationResponse {
    private String token;
    private String role;

    public AuthenticationResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public AuthenticationResponse() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static final class AuthenticationResponseBuilder {
        private String token;
        private String role;

        public static AuthenticationResponseBuilder builder() {
            return new AuthenticationResponseBuilder();
        }

        public AuthenticationResponseBuilder token(String token) {
            Assert.notNull(token, "token non può essere nullo");
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder role(String role) {
            Assert.notNull(role, "il ruolo non può essere nullo");
            this.role = role;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(token, role);
        }

    }


}
