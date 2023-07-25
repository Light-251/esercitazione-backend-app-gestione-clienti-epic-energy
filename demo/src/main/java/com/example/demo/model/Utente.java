package com.example.demo.model;

import com.example.demo.security.model.Role;
import com.example.demo.security.model.RoleNew;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class Utente implements UserDetails {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private Set<RoleNew> rolesNew = new HashSet<>();

    public Utente() {}

    public Utente(String username, String email, String password, String nome, String cognome, Set<RoleNew> rolesNew) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.rolesNew = rolesNew;
    }

    public Set<RoleNew> getRolesNew() {
        return rolesNew;
    }

    public void setRolesNew(Set<RoleNew> rolesNew) {
        this.rolesNew = rolesNew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesNew;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", rolesNew=" + rolesNew +
                '}';
    }

    public static final class UtenteBuilder {

        private Integer id;
        private String username;
        private String email;
        private String password;
        private String nome;
        private String cognome;
        private Set<RoleNew> rolesNew = new HashSet<>();

        private UtenteBuilder() {

        }

        public static UtenteBuilder builder() {
            return new UtenteBuilder();
        }

        public UtenteBuilder username(String username) {
            Assert.notNull(username, "Username non può essere null");
            this.username = username;
            return this;
        }

        public UtenteBuilder email(String email) {
            Assert.notNull(email, "L'email non può essere null");
            this.email = email;
            return this;
        }

        public UtenteBuilder password(String password) {
            Assert.notNull(password, "La password non può essere null");
            this.password = password;
            return this;
        }

        public UtenteBuilder nome(String nome) {
            Assert.notNull(nome, "Il nome non può essere null");
            this.nome = nome;
            return this;
        }

        public UtenteBuilder cognome(String cognome) {
            this.cognome = cognome;
            return this;
        }

        public UtenteBuilder rolesNew(Set<String> rolesNew) {
            Set<RoleNew> roles = new HashSet<>();

            rolesNew.forEach(role -> roles.add(new RoleNew(role)));

            this.rolesNew = roles;
            return this;
        }

        public Utente build() {
            return new Utente(this.username, this.email, this.password, this.nome, this.cognome, this.rolesNew);
        }

    }
}
