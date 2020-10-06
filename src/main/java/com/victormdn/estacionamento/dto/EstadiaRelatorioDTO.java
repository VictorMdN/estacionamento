package com.victormdn.estacionamento.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EstadiaRelatorioDTO {

    private Long id;

    private VeiculoPublicDTO veiculo;

    private Date entrada;

    private Date saida;

}
