package com.example.demo.controller;

import com.example.demo.dto.FatturaDTO;
import com.example.demo.model.Fattura;
import com.example.demo.service.FatturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/fattura/")
public class FatturaController {

    FatturaService fatturaService;

    public FatturaController(FatturaService fatturaService) {
        this.fatturaService = fatturaService;
    }

    @GetMapping("get")
    public ResponseEntity<?> getFatture(@RequestParam(required = false) String nomeCliente, @RequestParam(required = false) String stato,
                                        @RequestParam(required = false) String dataIni, @RequestParam(required = false) String dataFin,
                                        @RequestParam(required = false) String anno, @RequestParam(required = false) Double importoMin,
                                        @RequestParam(required = false) Double importoMax, @RequestParam(required = false) String order,
                                        @RequestParam(required = false) Integer idCliente, @RequestParam(required = false) Integer limit,
                                        @RequestParam(required = false) Integer pagina) {
        try {
            FatturaDTO fatture = fatturaService.getFatture(nomeCliente, stato, dataIni, dataFin, anno, importoMin, importoMax, idCliente, order, limit, pagina);
            if (fatture != null)
                return new ResponseEntity<>(fatture, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Effettua il login", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateFattura(@RequestParam("id") Integer id, @RequestParam("id_cliente") Integer idCliente, @RequestBody Fattura fattura) {

        boolean update = fatturaService.updateFattura(id, idCliente, fattura);
        if (update)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("errore", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveFattura(@RequestParam("id_cliente") Integer idCliente, @RequestBody Fattura fattura) {
        boolean save = fatturaService.saveFattura(fattura, idCliente);
        if (save)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("errore", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> removeFattura(@RequestParam("id") Integer id) {
        boolean remove = fatturaService.removeFatturaById(id);
        if (remove)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("errore", HttpStatus.UNAUTHORIZED);
    }

}
