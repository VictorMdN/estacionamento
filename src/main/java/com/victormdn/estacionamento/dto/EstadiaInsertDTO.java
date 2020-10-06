package com.victormdn.estacionamento.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EstadiaInsertDTO{

    @NotNull
    private Long veiculo;

    @NotNull
    private Long estabelecimento;

}
