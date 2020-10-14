package com.victormdn.estacionamento.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Veiculo {

    @Id
    @GeneratedValue
    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
