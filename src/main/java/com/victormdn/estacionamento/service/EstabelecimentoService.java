package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.*;
import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.repository.EstabelecimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoService {

    private static final String MSG_ID_VAZIO = "O campo id do estabelecimento deve ser um valor já existente.";
    private static final String MSG_ENDERECO_UNICA = "O campo 'placa' deve ser único.";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<EstabelecimentoPublicDTO> findAll() {
        return estabelecimentoRepository.findAll().parallelStream().map(this::estabelecimentoToEstabelecimentoPublicDTO).collect(Collectors.toList());
    }

    public EstabelecimentoPublicDTO getById(Long id) {
        return estabelecimentoToEstabelecimentoPublicDTO(validateId(id));
    }

    public EstabelecimentoPublicDTO save(EstabelecimentoInsertDTO estabelecimentoInsertDTO) {
        validEndereco(estabelecimentoInsertDTO.getEndereco());
        return estabelecimentoToEstabelecimentoPublicDTO(estabelecimentoRepository.save(estabelecimentoInsertDTOToEstabelecimento(estabelecimentoInsertDTO)));
    }

    public EstabelecimentoPublicDTO save(EstabelecimentoUpdateDTO estabelecimentoUpdateDTO) {
        Estabelecimento estabelecimento = validateId(estabelecimentoUpdateDTO.getId());
        if(!estabelecimento.getEndereco().equals(estabelecimentoUpdateDTO.getEndereco())) validEndereco(estabelecimentoUpdateDTO.getEndereco());
        return estabelecimentoToEstabelecimentoPublicDTO(estabelecimentoRepository.save(estabelecimentoUpdateDTOToEstabelecimento(estabelecimentoUpdateDTO, estabelecimento)));
    }

    public void deleteById(Long id) {
        validateId(id);
        estabelecimentoRepository.deleteById(id);
    }

    private void validEndereco(String endereco){
        if(estabelecimentoRepository.findByEndereco(endereco).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ENDERECO_UNICA);
    }

    public Estabelecimento validateId(Long id){
        return estabelecimentoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_VAZIO)
        );
    }

    public EstabelecimentoPublicDTO estabelecimentoToEstabelecimentoPublicDTO(Estabelecimento estabelecimento){
        return modelMapper.map(estabelecimento, EstabelecimentoPublicDTO.class);
    }

    public Estabelecimento estabelecimentoInsertDTOToEstabelecimento(EstabelecimentoInsertDTO estabelecimentoInsertDTO){
        return modelMapper.map(estabelecimentoInsertDTO, Estabelecimento.class);
    }

    public Estabelecimento estabelecimentoUpdateDTOToEstabelecimento(EstabelecimentoUpdateDTO estabelecimentoUpdateDTO, Estabelecimento estabelecimento){
        modelMapper.map(estabelecimentoUpdateDTO, estabelecimento);
        return estabelecimento;
    }

    public EstabelecimentoSumarioDTO estabelecimentoToEstabelecimentoSumarioDTO(Estabelecimento estabelecimento) {
        return modelMapper.map(estabelecimento, EstabelecimentoSumarioDTO.class);
    }

    public EstabelecimentoRelatorioDTO estabelecimentoToEstabelecimentoRelatorioDTO(Estabelecimento estabelecimento) {
        return modelMapper.map(estabelecimento, EstabelecimentoRelatorioDTO.class);
    }

}
