package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estabelecimento;

public class EstabelecimentoPublicDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private Integer vagasCarro;

    private Integer vagasMoto;

    public EstabelecimentoPublicDTO(Long id, String nome, String cnpj, String endereco, String telefone, Integer vagasCarro, Integer vagasMoto) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.vagasCarro = vagasCarro;
        this.vagasMoto = vagasMoto;
    }

    public static EstabelecimentoPublicDTO create(Estabelecimento estabelecimento) {
        return new EstabelecimentoPublicDTO(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getCnpj(),
                estabelecimento.getEndereco(),
                estabelecimento.getTelefone(),
                estabelecimento.getVagasCarro(),
                estabelecimento.getVagasMoto()
                );
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
