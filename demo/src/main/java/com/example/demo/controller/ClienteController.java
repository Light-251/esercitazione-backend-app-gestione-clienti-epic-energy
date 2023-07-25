package com.example.demo.controller;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/cliente/")
public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
        boolean save;
        save = clienteService.saveCliente(cliente);
        if (save)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("errore", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> removeCliente(@RequestParam("id") Integer id) {
        clienteService.removeCliente(id);
        return new ResponseEntity<>("success (Cliente eliminato) id=" + id, HttpStatus.OK);
    }

    @PostMapping("update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCliente(@RequestParam("id") Integer id, @RequestBody Cliente cliente) {
        boolean update;
        update = clienteService.updateCliente(id, cliente);
        if (update)
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("errore", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("get")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getAllOrdered(@RequestParam(required = false) String nome, @RequestParam(required = false) String dataInserimentoIni,
                                           @RequestParam(required = false) String dataInserimentoFin, @RequestParam(required = false) String dataUltimoContattoIni,
                                           @RequestParam(required = false) String dataUltimoContattoFin, @RequestParam(required = false) String provincia,
                                           @RequestParam(required = false) String order, @RequestParam(required = false) Integer limit,
                                           @RequestParam(required = false) Integer pagina) {
        try {
            ClienteDTO response = clienteService.getClienti(nome, dataInserimentoIni, dataInserimentoFin, dataUltimoContattoIni, dataUltimoContattoFin, provincia, order, limit, pagina);
            if (response != null)
                return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ParseException e) {
            return new ResponseEntity<>("Controlla il formato della Data", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Effettua il login", HttpStatus.UNAUTHORIZED);
    }

}
