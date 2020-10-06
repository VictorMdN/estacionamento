package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Tipo;
import lombok.Data;

@Data
public class VeiculoUpdateDTO {

    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private Tipo tipo;

}
