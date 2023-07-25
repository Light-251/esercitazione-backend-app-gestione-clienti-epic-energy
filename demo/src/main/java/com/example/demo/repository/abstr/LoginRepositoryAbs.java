package com.example.demo.repository.abstr;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.model.Utente;

@Deprecated
public abstract class LoginRepositoryAbs {

    protected ConnectionHandler connectionHandler;

    public LoginRepositoryAbs(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public abstract Utente login(String username, String password);
}
