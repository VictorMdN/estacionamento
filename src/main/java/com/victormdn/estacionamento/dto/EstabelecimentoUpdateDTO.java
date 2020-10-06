package com.victormdn.estacionamento.dto;

import lombok.Data;

@Data
public class EstabelecimentoUpdateDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private Integer vagasCarro;

    private Integer vagasMoto;

}
