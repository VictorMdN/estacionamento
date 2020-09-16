package com.victormdn.estacionamento.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Estadia {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Veiculo veiculo;

    @ManyToOne
    private Estabelecimento estabelecimento;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;

    public Estadia() {
    }

    public Estadia(Long id, Veiculo veiculo, Estabelecimento estabelecimento, Date entrada, Date saida) {
        this.id = id;
        this.veiculo = veiculo;
        this.estabelecimento = estabelecimento;
        this.entrada = entrada;
        this.saida = saida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
}
