package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Tipo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VeiculoInsertDTO {

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private String cor;

    @NotNull
    private String placa;

    @NotNull
    private Tipo tipo;

}
