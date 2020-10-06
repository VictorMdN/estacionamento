package com.victormdn.estacionamento.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EstabelecimentoInsertDTO {

    @NotNull
    private String nome;

    @NotNull
    private String cnpj;

    @NotNull
    private String endereco;

    @NotNull
    private String telefone;

    @NotNull
    private Integer vagasCarro;

    @NotNull
    private Integer vagasMoto;
}
