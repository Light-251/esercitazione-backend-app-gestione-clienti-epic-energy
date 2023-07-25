package com.example.demo.security.dto;
@Deprecated
public class RegisterRequest {

    private String nome;
    private String cognome;
    private String email;
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String firstName, String lastName, String email, String password) {
        this.nome = firstName;
        this.cognome = lastName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
