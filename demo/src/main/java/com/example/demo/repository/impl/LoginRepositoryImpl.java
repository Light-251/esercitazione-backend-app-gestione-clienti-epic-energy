package com.example.demo.repository.impl;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.model.Utente;
import com.example.demo.repository.abstr.LoginRepositoryAbs;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
@Repository
public class LoginRepositoryImpl extends LoginRepositoryAbs {

    public LoginRepositoryImpl(ConnectionHandler connectionHandler) {
        super(connectionHandler);
    }

    @Override
    public Utente login(String username, String password) {
        try {
            connectionHandler.openConnection();
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(
                    "SELECT * FROM utente WHERE username = ? AND password = ? ;");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            Utente utente = new Utente();
            if (resultSet.next()) {
                utente.setId(resultSet.getInt("id_utente"));
                utente.setUsername(resultSet.getString("username"));
                utente.setEmail(resultSet.getString("email"));
                utente.setPassword(resultSet.getString("password"));
                utente.setNome(resultSet.getString("nome"));
                utente.setCognome(resultSet.getString("cognome"));
            }
            if(utente.getId() == null)
                return null;

            //recupero ruoli
            PreparedStatement preparedStatementRuolo = connectionHandler.getConnection().prepareStatement(
                    "SELECT * FROM utente_ruolo WHERE utente_id = ?;");
            preparedStatementRuolo.setInt(1, utente.getId());

            ResultSet resultSetRuolo = preparedStatementRuolo.executeQuery();
            List<String> ruoli = new ArrayList<>();
            while (resultSetRuolo.next()) {
                ruoli.add(resultSetRuolo.getString("ruolo"));
            }
//            utente.setRuolo(ruoli);

            resultSetRuolo.close();
            preparedStatementRuolo.close();
            resultSet.close();
            statement.close();
            connectionHandler.closeConnection();
            return utente;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
