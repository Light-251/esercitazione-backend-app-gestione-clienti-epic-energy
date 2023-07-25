package com.example.demo.service;

import com.example.demo.model.Indirizzo;
import com.example.demo.repository.abstr.IndirizzoRepositoryAbs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoService {

    IndirizzoRepositoryAbs indirizzoRepository;

    public IndirizzoService(IndirizzoRepositoryAbs indirizzoRepository) {
        this.indirizzoRepository = indirizzoRepository;
    }

    public boolean updateIndirizzo(Integer id, Indirizzo indirizzo) {
        return indirizzoRepository.updateIndirizzoById(id, indirizzo);
    }

    public boolean saveIndirizzo(Indirizzo indirizzo) {
        return indirizzoRepository.saveIndirizzo(indirizzo);
    }

    public boolean removeIndirizzoById(Integer id) {
            return indirizzoRepository.removeIndirizzoById(id);
    }

    public List<Indirizzo> getAllIndirizzo() {
            return indirizzoRepository.getAllIndirizzi();
    }
}
