package com.victormdn.estacionamento.controller;

import com.victormdn.estacionamento.dto.EstabelecimentoRelatorioDTO;
import com.victormdn.estacionamento.dto.EstabelecimentoSumarioDTO;
import com.victormdn.estacionamento.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/sumarios/{id}")
    public EstabelecimentoSumarioDTO sumario(@PathVariable("id") Long id){
        return relatorioService.sumario(id);
    }

    @GetMapping("/relatorios/{id}")
    public EstabelecimentoRelatorioDTO relatorio(@PathVariable("id") Long id){
        return relatorioService.relatorio(id);
    }

}
