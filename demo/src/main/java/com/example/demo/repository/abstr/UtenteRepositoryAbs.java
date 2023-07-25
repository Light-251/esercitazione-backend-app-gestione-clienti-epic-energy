package com.example.demo.repository.abstr;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.model.Utente;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public abstract class UtenteRepositoryAbs {

    protected ConnectionHandler connectionHandler;

    public UtenteRepositoryAbs(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public abstract List<Utente> getAllUtenti();

    public abstract boolean removeUtenteById(Integer id);

    public abstract boolean updateUtenteById(Integer id, Utente utente);

    public abstract boolean saveUtente(Utente utente);

    public abstract UserDetails findByUsername(String username);

}
