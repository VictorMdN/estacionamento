package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Tipo;
import com.victormdn.estacionamento.model.Veiculo;

public class VeiculoPublicDTO {

    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private Tipo tipo;

    public VeiculoPublicDTO(Veiculo veiculo){
        this(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getPlaca(),
                veiculo.getTipo()
        );
    }

    public VeiculoPublicDTO(Long id, String marca, String modelo, String cor, String placa, Tipo tipo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.tipo = tipo;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
