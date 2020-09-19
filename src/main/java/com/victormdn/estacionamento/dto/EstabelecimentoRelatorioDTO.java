package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Tipo;
import com.victormdn.estacionamento.service.EstabelecimentoService;
import com.victormdn.estacionamento.service.EstadiaService;

import java.util.ArrayList;
import java.util.List;

public class EstabelecimentoRelatorioDTO extends EstabelecimentoPublicDTO{

    private Integer vagasCarroVazias;

    private Integer vagasMotoVazias;

    private List<EstadiaRelatorioDTO> carros = new ArrayList<>();

    private List<EstadiaRelatorioDTO> motos = new ArrayList<>();

    public EstabelecimentoRelatorioDTO(Long id, EstabelecimentoService estabelecimentoService, EstadiaService estadiaService) {
        super(estabelecimentoService.findById(id).get());
        estadiaService.getEstadiaRepository().findByEstabelecimento(estabelecimentoService.findById(id).get())
                .forEach(i -> {
                    if(i.getVeiculo().getTipo().equals(Tipo.CARRO)) carros.add(new EstadiaRelatorioDTO(i));
                    if(i.getVeiculo().getTipo().equals(Tipo.MOTO)) motos.add(new EstadiaRelatorioDTO(i));
                })
        ;
        vagasCarroVazias = getVagasCarro() - carros.size();
        vagasMotoVazias = getVagasMoto() - motos.size();
    }

    public Integer getVagasCarroVazias() {
        return vagasCarroVazias;
    }

    public Integer getVagasMotoVazias() {
        return vagasMotoVazias;
    }

    public List<EstadiaRelatorioDTO> getCarros() {
        return carros;
    }

    public List<EstadiaRelatorioDTO> getMotos() {
        return motos;
    }
}
