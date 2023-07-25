package com.example.demo.controller;

import com.example.demo.model.Utente;
import com.example.demo.service.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente/")
public class UtenteController {

    UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("get")
    public List<Utente> getAllUtenti() {
        return utenteService.getAllUtenti();
    }

    @PostMapping("update")
    public boolean updateUtente(@RequestParam Integer id, @RequestBody Utente utente) {
        return utenteService.updateUtente(id, utente);
    }

    @DeleteMapping("remove")
    public boolean deleteUtenteById(@RequestParam Integer id) {
        return utenteService.removeUtenteById(id);
    }
}
