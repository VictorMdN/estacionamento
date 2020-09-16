package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estabelecimento;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EstabelecimentoInsertDTO {

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private Integer vagasCarro;

    private Integer vagasMoto;

    public Estabelecimento toEstabelecimento() {
        if(nome == null
                || cnpj == null
                || endereco == null
                || telefone == null
                || vagasCarro == null
                || vagasMoto == null
        ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algum campo está vazio.");
        return new Estabelecimento(null, nome, cnpj, endereco, telefone, vagasCarro, vagasMoto);
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
