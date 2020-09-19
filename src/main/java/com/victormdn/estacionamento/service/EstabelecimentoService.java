package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.EstabelecimentoInsertDTO;
import com.victormdn.estacionamento.dto.EstabelecimentoPublicDTO;
import com.victormdn.estacionamento.dto.EstabelecimentoUpdateDTO;
import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<EstabelecimentoPublicDTO> findAll() {
        List<EstabelecimentoPublicDTO> ret = new ArrayList<>();
        for (Estabelecimento estabelecimento : estabelecimentoRepository.findAll()) ret.add(new EstabelecimentoPublicDTO(estabelecimento));
        return ret;
    }

    public EstabelecimentoPublicDTO getById(Long id) {
        return new EstabelecimentoPublicDTO(estabelecimentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public EstabelecimentoPublicDTO save(EstabelecimentoInsertDTO estabelecimentoInsertDTO) {
        if(estabelecimentoRepository.countByEndereco(estabelecimentoInsertDTO.getEndereco()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'endereco' deve ser único.");
        }
        return new EstabelecimentoPublicDTO(estabelecimentoRepository.save(estabelecimentoInsertDTO.toEstabelecimento()));
    }

    public EstabelecimentoPublicDTO save(EstabelecimentoUpdateDTO estabelecimentoUpdateDTO) {
        validateId(estabelecimentoUpdateDTO.getId());
        if(estabelecimentoRepository.countByEndereco(estabelecimentoUpdateDTO.getEndereco()) > 0
            && !estabelecimentoRepository.findById(estabelecimentoUpdateDTO.getId()).get().getEndereco().equals(estabelecimentoUpdateDTO.getEndereco()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'endereco' deve ser único.");
        return new EstabelecimentoPublicDTO(estabelecimentoRepository.save(estabelecimentoUpdateDTO.toEstabelecimento(estabelecimentoRepository.findById(estabelecimentoUpdateDTO.getId()).get())));
    }

    public void deleteById(Long id) {
        validateId(id);
        estabelecimentoRepository.deleteById(id);
    }

    public void validateId(Long id){
        if(id == null || !estabelecimentoRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo id do estabelecimento deve ser um valor já existente.");
    }

    public Optional<Estabelecimento> findById(Long id) {
        return estabelecimentoRepository.findById(id);
    }
}
