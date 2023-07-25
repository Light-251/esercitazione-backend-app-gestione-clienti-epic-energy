package com.example.demo.service;

import com.example.demo.dto.FatturaDTO;
import com.example.demo.model.Fattura;
import com.example.demo.repository.abstr.FatturaRepositoryAbs;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class FatturaService {

    FatturaRepositoryAbs fatturaRepository;

    public FatturaService(FatturaRepositoryAbs fatturaRepository) {
        this.fatturaRepository = fatturaRepository;
    }

    public boolean updateFattura(Integer id, Integer idCliente, Fattura fattura) {
        return fatturaRepository.updateFattura(id, idCliente, fattura);
    }

    public boolean saveFattura(Fattura fattura, Integer idCliente) {
        return fatturaRepository.saveFattura(fattura, idCliente);
    }

    public boolean removeFatturaById(Integer id) {
            return fatturaRepository.removeFatturaByd(id);
    }

    public FatturaDTO getFatture(String nomeCliente, String stato, String dataIni, String dataFin, String anno, Double importoMin,
                                 Double importoMax, Integer idCliente, String order, Integer limit, Integer pagina) throws ParseException {
            return fatturaRepository.getFatture(nomeCliente, stato, dataIni, dataFin, anno, importoMin, importoMax, idCliente, order, limit, pagina);
    }

}
