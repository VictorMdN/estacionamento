package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estadia;
import com.victormdn.estacionamento.model.Veiculo;

import java.util.Date;

public class EstadiaRelatorioDTO {

    private Long id;

    private Veiculo veiculo;

    private Date entrada;

    private Date saida;

    public EstadiaRelatorioDTO(Estadia estadia) {
        this(
                estadia.getId(),
                estadia.getVeiculo(),
                estadia.getEntrada(),
                estadia.getSaida()
        );
    }

    public EstadiaRelatorioDTO(Long id, Veiculo veiculo, Date entrada, Date saida) {
        this.id = id;
        this.veiculo = veiculo;
        this.entrada = entrada;
        this.saida = saida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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
