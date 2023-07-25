package com.example.demo.controller;

import com.example.demo.model.Indirizzo;
import com.example.demo.service.IndirizzoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indirizzo/")
public class IndirizzoController {

    IndirizzoService indirizzoService;

    public IndirizzoController(IndirizzoService indirizzoService) {
        this.indirizzoService = indirizzoService;
    }

    @PostMapping("update")
    public ResponseEntity<?> updateIndirizzo(@RequestParam Integer id, @RequestBody Indirizzo indirizzo) {
        boolean update = indirizzoService.updateIndirizzo(id, indirizzo);
        if (update)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("errore", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("add")
    public ResponseEntity<?> saveIndirizzo(@RequestBody Indirizzo indirizzo) {
        boolean save = indirizzoService.saveIndirizzo(indirizzo);
        if (save)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("errore", HttpStatus.OK);
    }

    @GetMapping("get")
    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoService.getAllIndirizzo();
    }

    @DeleteMapping("remove")
    public ResponseEntity<?> removeIndirizzoById(@RequestParam Integer id) {
        boolean remove = indirizzoService.removeIndirizzoById(id);
        if (remove)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("error", HttpStatus.UNAUTHORIZED);
    }
}
