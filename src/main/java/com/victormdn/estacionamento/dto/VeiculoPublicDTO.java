package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoPublicDTO {

    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private Tipo tipo;

}
