package com.example.demo;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.repository.impl.ClienteRepositoryImpl;
import com.example.demo.service.ClienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DemoApplication.class, args);
    }

}
