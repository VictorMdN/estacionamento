package com.victormdn.estacionamento.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Estabelecimento {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private Integer vagasCarro;

    private Integer vagasMoto;

}
