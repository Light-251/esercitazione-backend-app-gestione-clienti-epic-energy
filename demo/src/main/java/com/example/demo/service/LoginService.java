package com.example.demo.service;

import com.example.demo.repository.abstr.LoginRepositoryAbs;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class LoginService {

    LoginRepositoryAbs loginRepository;
//    public static Utente loggedUtente = null;

    public LoginService(LoginRepositoryAbs loginRepository) {
        this.loginRepository = loginRepository;
    }

//    public boolean login(String username, String password) {
//        loggedUtente = loginRepository.login(username, password);
//        return loggedUtente != null;
//    }

//    public boolean logout() {
//        if (loggedUtente != null) {
//            loggedUtente = null;
//            return true;
//        }
//        return false;
//    }
}
