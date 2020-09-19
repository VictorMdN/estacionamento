package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estadia;

import java.util.ArrayList;
import java.util.List;

public class EstadiaHoraDTO {

    private int qnt = 0;

    private int hora;

    private List<EstadiaRelatorioDTO> veiculos = new ArrayList<>();

    public EstadiaHoraDTO(int hora) {
        this.hora = hora;
    }

    public void push(Estadia estadia) {
        veiculos.add(new EstadiaRelatorioDTO(estadia));
    }

    public int getQnt() {
        return qnt;
    }

    public int getHora() {
        return hora;
    }

    public List<EstadiaRelatorioDTO> getVeiculos() {
        return veiculos;
    }
}
