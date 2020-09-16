package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estadia;

import java.util.Date;

public class EstadiaUpdateDTO {

    private Long id;

    private Date entrada;

    private Date saida;

    public Estadia toEstadia(Estadia estadia) {
        if(entrada != null) estadia.setEntrada(entrada);
        if(saida != null) estadia.setSaida(saida);
        return estadia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
}
