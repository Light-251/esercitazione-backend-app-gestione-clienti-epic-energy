package com.example.demo.service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.repository.abstr.ClienteRepositoryAbs;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ClienteService {

    ClienteRepositoryAbs clienteRepository;

    public ClienteService(ClienteRepositoryAbs clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * @param cliente da salvare nel DB
     * @return true se viene aggiunto correttamente, false altrimenti
     */
    public boolean saveCliente(Cliente cliente) {
        return clienteRepository.saveCliente(cliente);
    }

    /**
     * @param id del Cliente da rimuovere
     * @return true se viene rimosso correttamente, false altrimenti
     */
    public boolean removeCliente(Integer id) {
        return clienteRepository.removeClienteById(id);
    }

    /**
     * @param id      del CLiente da aggiornare
     * @param cliente è l'oggetto con i campi aggiornati da salvare nel DB
     * @return true se la modifica è andata buon fine, false altrimenti
     */
    public boolean updateCliente(Integer id, Cliente cliente) {
        return clienteRepository.updateClienteById(id, cliente);
    }

    public ClienteDTO getClienti(String nome, String dataInserimentoIni, String dataInserimentoFin, String dataUltimoContattoIni,
                                 String dataUltimoContattoFin, String provincia, String order, Integer limit, Integer pagina) throws ParseException {
        return clienteRepository.getClienti(nome, dataInserimentoIni, dataInserimentoFin, dataUltimoContattoIni, dataUltimoContattoFin, provincia, order, limit, pagina);
    }

}
