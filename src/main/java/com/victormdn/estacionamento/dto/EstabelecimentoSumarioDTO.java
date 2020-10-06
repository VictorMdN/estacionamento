package com.victormdn.estacionamento.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EstabelecimentoSumarioDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private Map<Integer, List<EstadiaRelatorioDTO>> horas;

}
