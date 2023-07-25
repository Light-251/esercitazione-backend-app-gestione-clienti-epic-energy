package com.example.demo.repository.abstr;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.model.Indirizzo;

import java.util.List;

public abstract class IndirizzoRepositoryAbs {

    protected ConnectionHandler connectionHandler;

    public IndirizzoRepositoryAbs(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public abstract List<Indirizzo> getAllIndirizzi();

    public abstract boolean removeIndirizzoById(Integer id);

    public abstract boolean updateIndirizzoById(Integer id, Indirizzo indirizzo);

    public abstract boolean saveIndirizzo(Indirizzo indirizzo);
}
