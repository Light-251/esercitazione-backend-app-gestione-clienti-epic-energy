package com.example.demo.repository.impl;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.model.Utente;
import com.example.demo.repository.abstr.UtenteRepositoryAbs;
import com.example.demo.security.model.RoleNew;
import com.example.demo.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UtenteRepositoryImpl extends UtenteRepositoryAbs {

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UtenteRepositoryImpl(
            ConnectionHandler connectionHandler)
//            ,PasswordEncoder passwordEncoder)
    {
        super(connectionHandler);
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Utente> getAllUtenti() {
        try {
            connectionHandler.openConnection();
            Statement statement = connectionHandler.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM utente;");
            PreparedStatement statementRuolo = connectionHandler.getConnection().prepareStatement(
                    "SELECT * FROM utente_ruolo WHERE utente_id=?;");

            List<Utente> utenti = new ArrayList<>();
            while (resultSet.next()) {
                Utente utente = new Utente();

                utente.setId(resultSet.getInt("id_utente"));
                utente.setUsername(resultSet.getString("username"));
                utente.setEmail(resultSet.getString("email"));
                utente.setPassword(resultSet.getString("password"));
                utente.setNome(resultSet.getString("nome"));
                utente.setCognome(resultSet.getString("cognome"));

                statementRuolo.setInt(1, utente.getId());
                ResultSet resultSetRuolo = statementRuolo.executeQuery();
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                while (resultSetRuolo.next()) {

                    String ruolo = resultSetRuolo.getString("ruolo");

//                    if (ruolo.equals(Role.ADMIN.name())) {
//                        utente.getRoles().add(Role.ADMIN);
//                        Role.ADMIN.getPermissions().forEach(auth ->
//                                authorities.add(new SimpleGrantedAuthority(auth.name()))
//                        );
//                    }
//
//                    if (ruolo.equals(Role.USER.name())) {
//                        utente.getRoles().add(Role.USER);
//                        Role.USER.getPermissions().forEach(auth ->
//                                authorities.add(new SimpleGrantedAuthority(auth.name()))
//                        );
//                    }
//                    utente.setAuthorities(authorities);

                    utente.getRolesNew().add(new RoleNew(ruolo));

                }

                utenti.add(utente);
            }

            resultSet.close();
            statement.close();
            connectionHandler.closeConnection();
            return utenti;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removeUtenteById(Integer id) {
        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement(
                    "DELETE FROM utente WHERE id_utente = ?;");
            preparedStatement.setInt(1, id);

            PreparedStatement preparedStatementStato = connectionHandler.getConnection().prepareStatement(
                    "DELETE FROM utente_ruolo WHERE utente_id = ?;");
            preparedStatementStato.setInt(1, id);

            int rowsAffected = preparedStatementStato.executeUpdate();
            rowsAffected += preparedStatement.executeUpdate();

            preparedStatementStato.close();
            preparedStatement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUtenteById(Integer id, Utente utente) {
        try {
            connectionHandler.openConnection();
            Statement statement = connectionHandler.getConnection().createStatement();

            String sql = QueryUtils.dinamicQueryUpdateUtente(id, utente);
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
    public boolean saveUtente(Utente utente) {
        System.out.println("utente = " + utente.toString());
        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement(
                    "INSERT INTO utente (username, email, password, nome, cognome) VALUES (?,?,?,?,?);");

            String encodedPassword = passwordEncoder.encode(utente.getPassword());

            preparedStatement.setString(1, utente.getUsername());
            preparedStatement.setString(2, utente.getEmail());
            preparedStatement.setString(3, "{bcrypt}" + encodedPassword);
            preparedStatement.setString(4, utente.getNome());
            preparedStatement.setString(5, utente.getCognome());

            int rowsAffected = preparedStatement.executeUpdate();

            PreparedStatement preparedStatementGetIdUtente = connectionHandler.getConnection().prepareStatement(
                    "SELECT id_utente FROM utente WHERE username = ?;");
            preparedStatementGetIdUtente.setString(1, utente.getUsername());
            ResultSet resultSetIdUtente = preparedStatementGetIdUtente.executeQuery();

            int idUtente = -1;
            if (resultSetIdUtente.next())
                idUtente = resultSetIdUtente.getInt("id_utente");

            resultSetIdUtente.close();
            preparedStatementGetIdUtente.close();

            PreparedStatement preparedStatementRuolo = connectionHandler.getConnection().prepareStatement(
                    "INSERT INTO utente_ruolo (utente_id, ruolo) VALUES (?,?);");
            preparedStatementRuolo.setInt(1, idUtente);

            for (RoleNew ruolo : utente.getRolesNew()) {
                preparedStatementRuolo.setString(2, ruolo.getAuthority());
                preparedStatementRuolo.executeUpdate();
            }

            preparedStatement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDetails findByUsername(String username) {
        try {
            connectionHandler.openConnection();
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(
                    "SELECT * FROM utente WHERE username = ?;");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Utente utente = new Utente();

                utente.setId(resultSet.getInt("id_utente"));
                utente.setUsername(resultSet.getString("username"));
                utente.setEmail(resultSet.getString("email"));
                utente.setPassword(resultSet.getString("password"));
                utente.setNome(resultSet.getString("nome"));
                utente.setCognome(resultSet.getString("cognome"));

                resultSet.close();
                statement.close();

                PreparedStatement statementRuolo = connectionHandler.getConnection().prepareStatement(
                        "SELECT ruolo FROM utente_ruolo WHERE utente_id=?;");
                statementRuolo.setInt(1, utente.getId());
                ResultSet resultSetRuolo = statementRuolo.executeQuery();

                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

                while (resultSetRuolo.next()) {
                    String ruolo = resultSetRuolo.getString("ruolo");
                    utente.getRolesNew().add(new RoleNew(ruolo));
                }

                resultSetRuolo.close();
                statementRuolo.close();
                connectionHandler.closeConnection();

                return utente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UsernameNotFoundException("Utente non trovato");
    }

}
