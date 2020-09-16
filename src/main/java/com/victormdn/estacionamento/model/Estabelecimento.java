package com.victormdn.estacionamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Estabelecimento {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cnpj;

    @Column(
            nullable = false,
            unique = true
    )
    private String endereco;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private Integer vagasCarro;

    @Column(nullable = false)
    private Integer vagasMoto;

    public Estabelecimento() {
    }

    public Estabelecimento(Long id, String nome, String cnpj, String endereco, String telefone, Integer vagasCarro, Integer vagasMoto) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.vagasCarro = vagasCarro;
        this.vagasMoto = vagasMoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getVagasCarro() {
        return vagasCarro;
    }

    public void setVagasCarro(Integer vagasCarro) {
        this.vagasCarro = vagasCarro;
    }

    public Integer getVagasMoto() {
        return vagasMoto;
    }

    public void setVagasMoto(Integer vagasMoto) {
        this.vagasMoto = vagasMoto;
    }
}
