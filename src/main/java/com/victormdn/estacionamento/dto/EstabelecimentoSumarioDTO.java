package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.service.EstabelecimentoService;
import com.victormdn.estacionamento.service.EstadiaService;

import java.util.ArrayList;
import java.util.List;

public class EstabelecimentoSumarioDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private List<EstadiaHoraDTO> horas = new ArrayList<>();

    public EstabelecimentoSumarioDTO(Long id, EstabelecimentoService estabelecimentoService, EstadiaService estadiaService) {
        Estabelecimento estabelecimento = estabelecimentoService.findById(id).get();
        this.id = id;
        this.nome = estabelecimento.getNome();
        this.cnpj = estabelecimento.getCnpj();
        this.endereco = estabelecimento.getEndereco();

        for (int i = 0; i < 24; i++) horas.add(new EstadiaHoraDTO(i));

        estadiaService.getEstadiaRepository().findByEstabelecimento(estabelecimentoService.findById(id).get())
                .forEach(i -> {
                    for (int j = 0; j < 24; j++) if(i.getEntrada().getHours() % 24 == j) horas.get(j).push(i);
                })
        ;

        for (int i = 0, total = 24; i < total; i++) if(horas.get(i).getVeiculos().size() == 0){
            horas.remove(i);
            i--;
            total--;
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<EstadiaHoraDTO> getHoras() {
        return horas;
    }
}
