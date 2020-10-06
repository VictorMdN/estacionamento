package com.victormdn.estacionamento.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EstadiaUpdateDTO {

    private Long id;

    private Date entrada;

    private Date saida;

}
