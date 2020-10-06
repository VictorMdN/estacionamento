package com.victormdn.estacionamento.controller;

import com.victormdn.estacionamento.dto.EstadiaInsertDTO;
import com.victormdn.estacionamento.dto.EstadiaPublicDTO;
import com.victormdn.estacionamento.dto.EstadiaUpdateDTO;
import com.victormdn.estacionamento.service.EstadiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estadias")
public class EstadiaController {

    @Autowired
    private EstadiaService estadiaService;

    @GetMapping
    public List<EstadiaPublicDTO> getAll(){
        return estadiaService.findAll();
    }

    @GetMapping("/{id}")
    public EstadiaPublicDTO get(@PathVariable Long id){
        return estadiaService.getById(id);
    }

    @PostMapping
    public EstadiaPublicDTO post(@RequestBody @Valid EstadiaInsertDTO estadiaInsertDTO){
        return estadiaService.save(estadiaInsertDTO);
    }

    @PutMapping("/sair/{id}")
    public EstadiaPublicDTO setSaida(@PathVariable Long id){
        return estadiaService.setSaida(id);
    }

    @PutMapping
    public EstadiaPublicDTO put(@RequestBody EstadiaUpdateDTO estadiaUpdateDTO){
        return estadiaService.save(estadiaUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        estadiaService.deleteById(id);
    }

}
