package com.example.demo.security.authentication;

import com.example.demo.security.dto.UtenteRegistrationDTO;
import com.example.demo.model.Utente;
import com.example.demo.repository.abstr.UtenteRepositoryAbs;
import com.example.demo.security.JwtService;
import com.example.demo.security.dto.AuthenticationRequest;
import com.example.demo.security.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final UtenteRepositoryAbs utenteRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UtenteRepositoryAbs utenteRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.utenteRepository = utenteRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UtenteRegistrationDTO request) {

        Utente user = Utente.UtenteBuilder.builder()
                .username(request.getUsername())
                .nome(request.getNome())
                .cognome(request.getCognome())
                .email(request.getEmail())
                .password(request.getPassword())
                .rolesNew(request.getRolesNew())
                .build();

        utenteRepository.saveUtente(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.AuthenticationResponseBuilder.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails user = utenteRepository.findByUsername(request.getUsername());
            String token = jwtService.generateToken(user);

            String role = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            return AuthenticationResponse.AuthenticationResponseBuilder.builder()
                    .token(token)
                    .role(role)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
