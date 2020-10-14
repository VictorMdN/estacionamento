package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.VeiculoInsertDTO;
import com.victormdn.estacionamento.dto.VeiculoPublicDTO;
import com.victormdn.estacionamento.dto.VeiculoUpdateDTO;
import com.victormdn.estacionamento.model.Veiculo;
import com.victormdn.estacionamento.repository.VeiculoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    public static final String MSG_PLACA_UNICA = "O campo 'placa' deve ser único.";
    public static final String MSG_ID_VAZIO = "O campo id do veículo deve ser um valor de um id de um veículo existente.";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoPublicDTO> findAll() {
        return veiculoRepository.findAll().parallelStream().map(this::veiculoToVeiculoPublicDTO).collect(Collectors.toList());
    }

    public VeiculoPublicDTO getById(Long id) {
        return veiculoToVeiculoPublicDTO(validateId(id));
    }

    public VeiculoPublicDTO save(VeiculoInsertDTO veiculoInsertDTO) {
        validateNewPlaca(veiculoInsertDTO.getPlaca());
        return veiculoToVeiculoPublicDTO(veiculoRepository.save(veiculoInsertDTOToVeiculo(veiculoInsertDTO)));
    }

    public VeiculoPublicDTO save(VeiculoUpdateDTO veiculoUpdateDTO) {
        Veiculo veiculo = validateId(veiculoUpdateDTO.getId());
        if(!veiculo.getPlaca().equals(veiculoUpdateDTO.getPlaca())) validateNewPlaca(veiculoUpdateDTO.getPlaca());
        return veiculoToVeiculoPublicDTO(veiculoRepository.save(veiculoUpdateDTOToVeiculo(veiculoUpdateDTO, veiculo)));
    }

    public void deleteById(Long id) {
        validateId(id);
        veiculoRepository.deleteById(id);
    }

    public Veiculo validateId(Long id){
        return veiculoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_VAZIO)
        );
    }

    public void validateNewPlaca(String placa){
        if(veiculoRepository.findByPlaca(placa).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_PLACA_UNICA);
    }

    public VeiculoPublicDTO veiculoToVeiculoPublicDTO(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoPublicDTO.class);
    }

    public Veiculo veiculoInsertDTOToVeiculo(VeiculoInsertDTO veiculoInsertDTO){
        return modelMapper.map(veiculoInsertDTO, Veiculo.class);
    }

    public Veiculo veiculoUpdateDTOToVeiculo(VeiculoUpdateDTO veiculoUpdateDTO, Veiculo veiculo){
        modelMapper.map(veiculoUpdateDTO, veiculo);
        return veiculo;
    }

}
