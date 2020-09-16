package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estadia;

import java.util.Date;

public class EstadiaPublicDTO {

    private Long id;

    private Long veiculo;

    private Long estabelecimento;

    private Date entrada;

    private Date saida;

    public EstadiaPublicDTO(Long id, Long veiculo, Long estabelecimento, Date entrada, Date saida) {
        this.id = id;
        this.veiculo = veiculo;
        this.estabelecimento = estabelecimento;
        this.entrada = entrada;
        this.saida = saida;
    }

    public static EstadiaPublicDTO create(Estadia estadia) {
        return new EstadiaPublicDTO(
                estadia.getId(),
                estadia.getVeiculo().getId(),
                estadia.getEstabelecimento().getId(),
                estadia.getEntrada(),
                estadia.getSaida()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Long veiculo) {
        this.veiculo = veiculo;
    }

    public Long getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Long estabelecimento) {
        this.estabelecimento = estabelecimento;
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
