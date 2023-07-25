package com.example.demo.service;

import com.example.demo.model.Utente;
import com.example.demo.repository.abstr.UtenteRepositoryAbs;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService implements UserDetailsService {

    private UtenteRepositoryAbs utenteRepository;

    public UtenteService(UtenteRepositoryAbs utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

//    public boolean saveUtente(Utente utente) { // todo eliminare, c'è il metodo register nella classe AuthenticationService
//        return utenteRepository.saveUtente(utente);
//    }

    public boolean removeUtenteById(Integer id) {
        return utenteRepository.removeUtenteById(id);
    }

    public boolean updateUtente(Integer id, Utente utente) {
        return utenteRepository.updateUtenteById(id, utente);
    }

    public List<Utente> getAllUtenti() {
        return utenteRepository.getAllUtenti();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenteRepository.findByUsername(username);
    }
}
