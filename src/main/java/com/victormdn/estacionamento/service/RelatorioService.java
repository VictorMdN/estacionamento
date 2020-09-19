package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.EstabelecimentoRelatorioDTO;
import com.victormdn.estacionamento.dto.EstabelecimentoSumarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private EstadiaService estadiaService;

    public EstabelecimentoSumarioDTO sumario(Long id) {
        estabelecimentoService.validateId(id);
        return new EstabelecimentoSumarioDTO(id, estabelecimentoService, estadiaService);
    }

    public EstabelecimentoRelatorioDTO relatorio(Long id) {
        estabelecimentoService.validateId(id);
        return new EstabelecimentoRelatorioDTO(id, estabelecimentoService, estadiaService);
    }
}
