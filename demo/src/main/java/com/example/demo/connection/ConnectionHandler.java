package com.example.demo.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionHandler {

    @Autowired
    private Environment environment;

    private Connection connection;

    public ConnectionHandler() throws SQLException {
    }

    public void openConnection() throws SQLException {
        String URL = environment.getProperty("spring.datasource.url");
        String USER = environment.getProperty("spring.datasource.username");
        String PASS = environment.getProperty("spring.datasource.password");
        this.connection = DriverManager.getConnection(URL, USER, PASS);
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
