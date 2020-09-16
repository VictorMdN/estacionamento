package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VeiculoUpdateDTO {

    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private String tipo;

    public Veiculo toVeiculo(Veiculo veiculo) {
        if(marca != null) veiculo.setMarca(marca);
        if(modelo != null) veiculo.setModelo(modelo);
        if(cor != null) veiculo.setCor(cor);
        if(placa != null) veiculo.setPlaca(placa);
        if(tipo != null) veiculo.setTipo(tipo);
        return veiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
