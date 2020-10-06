package com.victormdn.estacionamento.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EstadiaPublicDTO {

    private Long id;

    private Long veiculo;

    private Long estabelecimento;

    private Date entrada;

    private Date saida;

}
