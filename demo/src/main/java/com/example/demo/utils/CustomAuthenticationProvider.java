//package com.example.demo.security;
//
//import com.example.demo.model.Utente;
//import com.example.demo.repository.abstr.UtenteRepositoryAbs;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UtenteRepositoryAbs utenteRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        Utente utente = (Utente) utenteRepository.findByUsername(username);
//        if (utente != null) {
//            if (encoder.matches(password, utente.getPassword())) {
//                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                for (String role : utente.getRuolo()) {
//                    authorities.add(new SimpleGrantedAuthority(role));
//                }
//                utente.setAuthorities(authorities);
//                return new UsernamePasswordAuthenticationToken(username, password, utente.getAuthorities());
//            } else {
//                throw new BadCredentialsException("password errata");
//            }
//        } else {
//            throw new BadCredentialsException("L'utente non esiste");
//        }
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
