package com.victormdn.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadiaPublicDTO {

    private Long id;

    private Long veiculo;

    private Long estabelecimento;

    private Date entrada;

    private Date saida;

}
