package com.example.demo.dto;

import com.example.demo.model.Cliente;
import com.example.demo.security.model.RoleNew;

import java.util.List;

public class ClienteDTO {

    private List<Cliente> clienti;
    private Integer numeroPagina;
    private Integer elementiTotali;
    private Integer elementiPerPagina;

    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(Integer numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public Integer getElementiTotali() {
        return elementiTotali;
    }

    public void setElementiTotali(Integer elementiTotali) {
        this.elementiTotali = elementiTotali;
    }

    public Integer getElementiPerPagina() {
        return elementiPerPagina;
    }

    public void setElementiPerPagina(Integer elementiPerPagina) {
        this.elementiPerPagina = elementiPerPagina;
    }
}
