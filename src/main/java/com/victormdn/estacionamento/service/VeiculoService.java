package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.VeiculoInsertDTO;
import com.victormdn.estacionamento.dto.VeiculoPublicDTO;
import com.victormdn.estacionamento.dto.VeiculoUpdateDTO;
import com.victormdn.estacionamento.model.Tipo;
import com.victormdn.estacionamento.model.Veiculo;
import com.victormdn.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoPublicDTO> findAll() {
        List<VeiculoPublicDTO> ret = new ArrayList<>();
        for (Veiculo veiculo : veiculoRepository.findAll()) ret.add(new VeiculoPublicDTO(veiculo));
        return ret;
    }

    public VeiculoPublicDTO getById(Long id) {
        return new VeiculoPublicDTO(veiculoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public VeiculoPublicDTO save(VeiculoInsertDTO veiculoInsertDTO) {
        validadeTipo(veiculoInsertDTO.getTipo());
        if(veiculoRepository.countByPlaca(veiculoInsertDTO.getPlaca()) > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'placa' deve ser único.");
        return new VeiculoPublicDTO(veiculoRepository.save(veiculoInsertDTO.toVeiculo()));
    }

    public VeiculoPublicDTO save(VeiculoUpdateDTO veiculoUpdateDTO) {
        validateId(veiculoUpdateDTO.getId());
        validadeTipo(veiculoUpdateDTO.getTipo());
        if(veiculoRepository.countByPlaca(veiculoUpdateDTO.getPlaca()) > 0
            && !veiculoRepository.findById(veiculoUpdateDTO.getId()).get().getPlaca().equals(veiculoUpdateDTO.getPlaca()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'placa' deve ser único.");
        return new VeiculoPublicDTO(veiculoRepository.save(veiculoUpdateDTO.toVeiculo(veiculoRepository.findById(veiculoUpdateDTO.getId()).get())));
    }

    public void deleteById(Long id) {
        validateId(id);
        veiculoRepository.deleteById(id);
    }

    public void validateId(Long id){
        if(id == null || !veiculoRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo id deve ser um valor já existente.");
    }

    public static void validadeTipo(Tipo tipo){
        if(!tipo.equals(Tipo.CARRO) && !tipo.equals(Tipo.MOTO))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo tipo deve ser 'carro' ou 'moto'.");
    }
}
