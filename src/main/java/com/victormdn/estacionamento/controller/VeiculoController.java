package com.victormdn.estacionamento.controller;

import com.victormdn.estacionamento.dto.VeiculoInsertDTO;
import com.victormdn.estacionamento.dto.VeiculoPublicDTO;
import com.victormdn.estacionamento.dto.VeiculoUpdateDTO;
import com.victormdn.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<VeiculoPublicDTO> getAll(){
        return veiculoService.findAll();
    }

    @GetMapping("/{id}")
    public VeiculoPublicDTO get(@PathVariable Long id){
        return veiculoService.getById(id);
    }

    @PostMapping()
    public VeiculoPublicDTO post(@RequestBody VeiculoInsertDTO veiculoInsertDTO){
        return veiculoService.save(veiculoInsertDTO);
    }

    @PutMapping()
    public VeiculoPublicDTO put(@RequestBody VeiculoUpdateDTO veiculoUpdateDTO){
        return veiculoService.save(veiculoUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        veiculoService.deleteById(id);
    }
}
