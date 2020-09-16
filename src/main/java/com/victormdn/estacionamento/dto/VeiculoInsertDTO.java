package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VeiculoInsertDTO {

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private String tipo;

    public Veiculo toVeiculo() {
        if(marca == null
                || modelo == null
                || cor == null
                || placa == null
                || tipo == null
        ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algum campo está vazio.");
        return new Veiculo(null, marca, modelo, cor, placa, tipo);
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
