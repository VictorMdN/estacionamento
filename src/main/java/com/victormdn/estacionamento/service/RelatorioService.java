package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.EstabelecimentoRelatorioDTO;
import com.victormdn.estacionamento.dto.EstabelecimentoSumarioDTO;
import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.model.Tipo;
import com.victormdn.estacionamento.repository.EstadiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private EstadiaService estadiaService;

    @Autowired
    private EstadiaRepository estadiaRepository;

    public EstabelecimentoSumarioDTO sumario(Long id) {

        Estabelecimento estabelecimento = estabelecimentoService.validateId(id);
        EstabelecimentoSumarioDTO estabelecimentoSumarioDTO = estabelecimentoService.estabelecimentoToEstabelecimentoSumarioDTO(estabelecimento);

        estabelecimentoSumarioDTO.setHoras(estadiaRepository.findByEstabelecimento(estabelecimento).stream()
                .map(estadiaService::estadiaToEstadiaRelatorioDTO)
                .collect(Collectors.groupingBy(estadiaRelatorioDTO -> estadiaRelatorioDTO.getEntrada().getHours()))
        );
        return estabelecimentoSumarioDTO;
    }

    public EstabelecimentoRelatorioDTO relatorio(Long id) {
        Estabelecimento estabelecimento = estabelecimentoService.validateId(id);
        EstabelecimentoRelatorioDTO estabelecimentoRelatorioDTO = estabelecimentoService.estabelecimentoToEstabelecimentoRelatorioDTO(estabelecimento);
        estabelecimentoRelatorioDTO.setVeiculos(estadiaRepository.findByEstabelecimentoAndSaidaIsNull(estabelecimento).stream()
                .map(estadiaService::estadiaToEstadiaRelatorioDTO)
                .collect(Collectors.groupingBy(estadiaRelatorioDTO -> estadiaRelatorioDTO.getVeiculo().getTipo()))
        );
        estabelecimentoRelatorioDTO.getVeiculos().computeIfAbsent(Tipo.CARRO, k -> new ArrayList<>());
        estabelecimentoRelatorioDTO.getVeiculos().computeIfAbsent(Tipo.MOTO, k -> new ArrayList<>());
        estabelecimentoRelatorioDTO.setVagasCarroVazias(estabelecimentoRelatorioDTO.getVagasCarro() - estabelecimentoRelatorioDTO.getVeiculos().get(Tipo.CARRO).size());
        estabelecimentoRelatorioDTO.setVagasMotoVazias(estabelecimentoRelatorioDTO.getVagasMoto() - estabelecimentoRelatorioDTO.getVeiculos().get(Tipo.MOTO).size());
        return estabelecimentoRelatorioDTO;
    }

}
