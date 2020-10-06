package com.victormdn.estacionamento.controller;

import com.victormdn.estacionamento.dto.EstabelecimentoInsertDTO;
import com.victormdn.estacionamento.dto.EstabelecimentoPublicDTO;
import com.victormdn.estacionamento.dto.EstabelecimentoUpdateDTO;
import com.victormdn.estacionamento.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping
    public List<EstabelecimentoPublicDTO> getAll(){
        return estabelecimentoService.findAll();
    }

    @GetMapping("/{id}")
    public EstabelecimentoPublicDTO get(@PathVariable Long id){
        return estabelecimentoService.getById(id);
    }

    @PostMapping
    public EstabelecimentoPublicDTO post(@RequestBody @Valid EstabelecimentoInsertDTO estabelecimentoInsertDTO){
        return estabelecimentoService.save(estabelecimentoInsertDTO);
    }

    @PutMapping
    public EstabelecimentoPublicDTO put(@RequestBody EstabelecimentoUpdateDTO estabelecimentoUpdateDTO){
        return estabelecimentoService.save(estabelecimentoUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        estabelecimentoService.deleteById(id);
    }

}
