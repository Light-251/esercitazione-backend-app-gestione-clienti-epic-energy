package com.example.demo.security.dto;

import io.jsonwebtoken.lang.Assert;

import java.util.HashSet;
import java.util.Set;

public class UtenteRegistrationDTO {

    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private Set<String> rolesNew = new HashSet<>();

    public UtenteRegistrationDTO() {
    }

    public UtenteRegistrationDTO(String username, String email, String password, String nome, String cognome, Set<String> rolesNew) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.rolesNew = rolesNew;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Set<String> getRolesNew() {
        return rolesNew;
    }

    @Override
    public String toString() {
        return "UtenteRegistrationDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", rolesNew=" + rolesNew +
                '}';
    }

}
