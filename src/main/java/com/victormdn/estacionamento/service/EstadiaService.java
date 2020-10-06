package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.*;
import com.victormdn.estacionamento.model.*;
import com.victormdn.estacionamento.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadiaService {

    private static final String MSG_VEICULO_ESTACIONADO = "O veiculo já está estacionado.";
    private static final String MSG_ID_VAZIO = "Os campos 'id' devem ser valores de registros já existentes.";
    private static final String MSG_SEM_VAGAS = "Não há vagas para o veículo.";
    private static final String MSG_ESTADIA_TERMINADA = "Esta estadia já foi finalizada.";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<EstadiaPublicDTO> findAll() {
        return estadiaRepository.findAll().parallelStream().map(this::estadiaToEstadiaPublicDTO).collect(Collectors.toList());
    }

    public EstadiaPublicDTO getById(Long id) {
        return estadiaToEstadiaPublicDTO(validateId(id, estadiaRepository));
    }

    public EstadiaPublicDTO save(EstadiaInsertDTO estadiaInsertDTO) {
        Veiculo veiculo = validateId(estadiaInsertDTO.getVeiculo(), veiculoRepository);
        Estabelecimento estabelecimento = validateId(estadiaInsertDTO.getEstabelecimento(), estabelecimentoRepository);

        if(estadiaRepository.findByVeiculoAndSaidaIsNull(validateId(estadiaInsertDTO.getVeiculo(), veiculoRepository)).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_VEICULO_ESTACIONADO);
        if(estadiaRepository.countByEstabelecimentoIdAndVeiculoTipoAndSaidaIsNull(estabelecimento.getId(), veiculo.getTipo()) >=
                (veiculo.getTipo().equals(Tipo.MOTO)
                        ? estabelecimento.getVagasMoto()
                        : estabelecimento.getVagasCarro()
                )
        ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_SEM_VAGAS);

        Estadia estadia = Estadia.builder()
                .veiculo(veiculo)
                .estabelecimento(estabelecimento)
                .entrada(new Date())
                .build();
        estadiaInsertDTOToEstadia(estadiaInsertDTO, estadia);
        return estadiaToEstadiaPublicDTO(estadiaRepository.save(estadia));
    }

    public EstadiaPublicDTO setSaida(Long id) {
        Estadia estadia = validateId(id, estadiaRepository);
        if(estadia.getSaida() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ESTADIA_TERMINADA);
        estadia.setSaida(new Date());
        return estadiaToEstadiaPublicDTO(estadiaRepository.save(estadia));
    }

    public EstadiaPublicDTO save(EstadiaUpdateDTO estadiaUpdateDTO) {
        Estadia estadia = validateId(estadiaUpdateDTO.getId(), estadiaRepository);
        estadiaUpdateDTOToEstadia(estadiaUpdateDTO, estadia);
        return estadiaToEstadiaPublicDTO(estadiaRepository.save(estadia));
    }

    public void deleteById(Long id) {
        validateId(id, estadiaRepository);
        estadiaRepository.deleteById(id);
    }

    public <U, T> T validateId(U id, JpaRepository<T, U> jpaRepository){
        return jpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_VAZIO)
        );
    }

    public EstadiaPublicDTO estadiaToEstadiaPublicDTO(Estadia estadia){
        EstadiaPublicDTO estadiaPublicDTO = modelMapper.map(estadia, EstadiaPublicDTO.class);
        estadiaPublicDTO.setVeiculo(estadia.getVeiculo().getId());
        estadiaPublicDTO.setEstabelecimento(estadia.getEstabelecimento().getId());
        return estadiaPublicDTO;
    }

    public void estadiaInsertDTOToEstadia(EstadiaInsertDTO estadiaInsertDTO, Estadia estadia){
        modelMapper.map(estadiaInsertDTO, estadia);
    }

    public void estadiaUpdateDTOToEstadia(EstadiaUpdateDTO estadiaUpdateDTO, Estadia estadia){
        modelMapper.map(estadiaUpdateDTO, estadia);
    }

    public EstadiaRelatorioDTO estadiaToEstadiaRelatorioDTO(Estadia estadia){
        return modelMapper.map(estadia, EstadiaRelatorioDTO.class);
    }

}
