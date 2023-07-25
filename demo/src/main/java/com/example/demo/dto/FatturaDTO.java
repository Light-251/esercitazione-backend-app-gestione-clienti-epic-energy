package com.example.demo.dto;

import com.example.demo.model.Fattura;

import java.util.List;

public class FatturaDTO {

    private List<Fattura> fatture;
    private Integer pagina;
    private Integer elementiTotali;
    private Integer elementiPerPagina;

    public List<Fattura> getFatture() {
        return fatture;
    }

    public void setFatture(List<Fattura> fatture) {
        this.fatture = fatture;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
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
