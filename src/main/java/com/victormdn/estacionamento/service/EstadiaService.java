package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.*;
import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.model.Estadia;
import com.victormdn.estacionamento.model.Tipo;
import com.victormdn.estacionamento.model.Veiculo;
import com.victormdn.estacionamento.repository.EstabelecimentoRepository;
import com.victormdn.estacionamento.repository.EstadiaRepository;
import com.victormdn.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EstadiaService {

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<EstadiaPublicDTO> findAll() {
        List<EstadiaPublicDTO> ret = new ArrayList<>();
        for (Estadia estadia : estadiaRepository.findAll()) ret.add(new EstadiaPublicDTO(estadia));
        return ret;
    }

    public EstadiaPublicDTO getById(Long id) {
        return new EstadiaPublicDTO(estadiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public EstadiaPublicDTO save(EstadiaInsertDTO estadiaInsertDTO) {
        validateId(estadiaInsertDTO.getVeiculo(), veiculoRepository);
        validateId(estadiaInsertDTO.getEstabelecimento(), estabelecimentoRepository);
        Veiculo veiculo = veiculoRepository.findById(estadiaInsertDTO.getVeiculo()).get();
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estadiaInsertDTO.getEstabelecimento()).get();
        if(estadiaRepository.estacionado(estadiaInsertDTO.getVeiculo()).size() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O veiculo já está estacionado.");
        }
        if(estadiaRepository.locados(estabelecimento.getId(), veiculo.getTipo()).size() >=
                (veiculo.getTipo().equals(Tipo.MOTO)
                        ? estabelecimento.getVagasMoto()
                        : estabelecimento.getVagasCarro()
                )
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não há vagas para o veículo.");
        }
        Estadia ret = estadiaInsertDTO.toEstadia(veiculo, estabelecimento);
        ret.setEntrada(new Date());
        return new EstadiaPublicDTO(estadiaRepository.save(ret));
    }

    public EstadiaPublicDTO setSaida(Long id) {
        validateId(id, estadiaRepository);
        Estadia estadia = estadiaRepository.findById(id).get();
        if(estadia.getSaida() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O veículo já está estacionado.");
        estadia.setSaida(new Date());
        estadiaRepository.save(estadia);
        return new EstadiaPublicDTO(estadia);
    }

    public EstadiaPublicDTO save(EstadiaUpdateDTO estadiaUpdateDTO) {
        validateId(estadiaUpdateDTO.getId(), estadiaRepository);

        return new EstadiaPublicDTO(estadiaRepository.save(estadiaUpdateDTO.toEstadia(estadiaRepository.findById(estadiaUpdateDTO.getId()).get())));
    }

    public void deleteById(Long id) {
        validateId(id, estadiaRepository);
        estadiaRepository.deleteById(id);
    }

    public void validateId(Long id, JpaRepository jpaRepository){
        if(id == null || !jpaRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo id do veículo e do estabelecimento devem ser valores já existentes.");
    }

    public EstadiaRepository getEstadiaRepository() {
        return estadiaRepository;
    }

    public void setEstadiaRepository(EstadiaRepository estadiaRepository) {
        this.estadiaRepository = estadiaRepository;
    }
}
