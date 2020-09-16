package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.model.Estadia;
import com.victormdn.estacionamento.model.Veiculo;

public class EstadiaInsertDTO{

    private Long veiculo;

    private Long estabelecimento;

    public Estadia toEstadia(Veiculo veiculo, Estabelecimento estabelecimento) {
        return new Estadia(null, veiculo, estabelecimento, null, null);
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
}
