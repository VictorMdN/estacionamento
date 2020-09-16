package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.service.EstabelecimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EstabelecimentoUpdateDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private Integer vagasCarro;

    private Integer vagasMoto;


    public Estabelecimento toEstabelecimento(Estabelecimento estabelecimento) {
        if(nome != null) estabelecimento.setNome(nome);
        if(cnpj != null) estabelecimento.setCnpj(cnpj);
        if(endereco != null) estabelecimento.setEndereco(endereco);
        if(telefone != null) estabelecimento.setTelefone(telefone);
        if(vagasCarro != null) estabelecimento.setVagasCarro(vagasCarro);
        if(vagasMoto != null) estabelecimento.setVagasMoto(vagasMoto);
        return estabelecimento;
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
