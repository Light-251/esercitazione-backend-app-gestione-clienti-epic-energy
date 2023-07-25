package com.example.demo.repository.impl;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.model.Indirizzo;
import com.example.demo.repository.abstr.IndirizzoRepositoryAbs;
import com.example.demo.utils.QueryUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IndirizzoRepositoryImpl extends IndirizzoRepositoryAbs {

    public IndirizzoRepositoryImpl(ConnectionHandler connectionHandler) {
        super(connectionHandler);
    }

    @Override
    public List<Indirizzo> getAllIndirizzi() {
        try {
            connectionHandler.openConnection();
            Statement statement = connectionHandler.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM indirizzo;");

            List<Indirizzo> indirizzi = new ArrayList<>();
            while (resultSet.next()) {
                Indirizzo indirizzo = new Indirizzo();

                indirizzo.setId(resultSet.getInt("id_indirizzo"));
                indirizzo.setVia(resultSet.getString("via"));
                indirizzo.setCivico(resultSet.getString("civico"));
                indirizzo.setLocalita(resultSet.getString("localita"));
                indirizzo.setCap(resultSet.getString("cap"));
                indirizzo.setProvincia(resultSet.getString("provincia"));
                indirizzo.setIdCliente(resultSet.getInt("id_cliente"));
                indirizzo.setTipo(resultSet.getString("tipo"));

                indirizzi.add(indirizzo);
            }

            resultSet.close();
            statement.close();
            connectionHandler.closeConnection();
            return indirizzi;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removeIndirizzoById(Integer id) {

        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement("DELETE FROM indirizzo WHERE id_indirizzo = ?;");
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateIndirizzoById(Integer id, Indirizzo indirizzo) {

        try {
            connectionHandler.openConnection();
            Statement statement = connectionHandler.getConnection().createStatement();

            String sql = QueryUtils.dinamicQueryUpdateIndirizzo(id, indirizzo);
            int rowsAffected = statement.executeUpdate(sql);

            statement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean saveIndirizzo(Indirizzo indirizzo) {
        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement("INSERT INTO indirizzo (via, civico, localita, cap, provincia, id_cliente, tipo) VALUES (?,?,?,?,?,?,?);");

            preparedStatement.setString(1, indirizzo.getVia());
            preparedStatement.setString(2, indirizzo.getCivico());
            preparedStatement.setString(3, indirizzo.getLocalita());
            preparedStatement.setString(4, indirizzo.getCap());
            preparedStatement.setString(5, indirizzo.getProvincia());
            preparedStatement.setInt(6, indirizzo.getIdCliente());
            preparedStatement.setString(7, indirizzo.getTipo());

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
