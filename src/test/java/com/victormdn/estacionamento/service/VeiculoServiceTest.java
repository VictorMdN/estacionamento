package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.VeiculoPublicDTO;
import com.victormdn.estacionamento.dto.VeiculoUpdateDTO;
import com.victormdn.estacionamento.model.Veiculo;
import com.victormdn.estacionamento.repository.VeiculoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VeiculoServiceTest {

    @Mock
    VeiculoRepository veiculoRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    VeiculoService veiculoService;

    @Test
    void save_IdIsNotPresent_Throws(){
        Long id = 1L;

        VeiculoUpdateDTO veiculoUpdateDTO = VeiculoUpdateDTO.builder()
                .id(id)
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException responseStatusException = assertThrows(
                ResponseStatusException.class,
                () -> veiculoService.save(veiculoUpdateDTO)
        );

        assertThat(responseStatusException.getMessage())
                .isEqualTo(new ResponseStatusException(HttpStatus.BAD_REQUEST, VeiculoService.MSG_ID_VAZIO).getMessage());
    }

    @Test
    void save_PlacaIsNotNew_Throws(){
        String placa = "";

        VeiculoUpdateDTO veiculoUpdateDTO = VeiculoUpdateDTO.builder()
                .id(1L)
                .placa(placa)
                .build();
        Veiculo veiculo = Veiculo.builder()
                .placa("1")
                .build();

        when(veiculoRepository.findById(any(Long.class))).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.findByPlaca(placa)).thenReturn(Optional.of(veiculo));

        ResponseStatusException responseStatusException = assertThrows(
                ResponseStatusException.class,
                () -> veiculoService.save(veiculoUpdateDTO)
        );

        assertThat(responseStatusException.getMessage())
                .isEqualTo(new ResponseStatusException(HttpStatus.BAD_REQUEST, VeiculoService.MSG_PLACA_UNICA).getMessage());
    }

    @Test
    void save_VeiculosSemRestricoes_NotNull(){
        String placa = "";

        VeiculoUpdateDTO veiculoUpdateDTO = VeiculoUpdateDTO.builder()
                .id(1L)
                .placa(placa)
                .build();
        VeiculoUpdateDTO veiculoUpdateDTO1 = VeiculoUpdateDTO.builder()
                .id(2L)
                .placa("1")
                .build();
        Veiculo veiculo = Veiculo.builder()
                .placa(placa)
                .build();
        VeiculoPublicDTO veiculoPublicDTO = new VeiculoPublicDTO();

        when(veiculoRepository.findById(any(Long.class))).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.findByPlaca(any(String.class))).thenReturn(Optional.empty());
        when(veiculoRepository.save(any(Veiculo.class))).then(returnsFirstArg());
        when(modelMapper.map(veiculo, VeiculoPublicDTO.class)).thenReturn(veiculoPublicDTO);

        VeiculoPublicDTO veiculoPublicDTO1 = veiculoService.save(veiculoUpdateDTO);
        VeiculoPublicDTO veiculoPublicDTO2 = veiculoService.save(veiculoUpdateDTO1);

        assertThat(veiculoPublicDTO1).isNotNull();
        assertThat(veiculoPublicDTO2).isNotNull();
    }

    @Test
    void validateId_IdIsPresent_Equals(){
        Long id = 1L;

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(Veiculo.builder()
                .id(id)
                .build()
        ));

        Veiculo veiculo = veiculoService.validateId(id);

        assertThat(veiculo.getId()).isEqualTo(id);
    }

    @Test
    void validateId_IdIsNotPresent_Throws(){
        Long id = 1L;

        when(veiculoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> veiculoService.validateId(id)
        );
    }

    @Test
    void validateNewPlaca_PlacaIsPresent_Throws(){
        String placa = "";

        when(veiculoRepository.findByPlaca(placa)).thenReturn(Optional.of(Veiculo.builder()
                    .placa(placa)
                    .build()
            ));

        assertThrows(
                ResponseStatusException.class,
                () -> veiculoService.validateNewPlaca(placa)
        );
    }

    @Test
    void validateNewPlaca_PlacaIsNotPresent_Null(){
        String placa = "";

        when(veiculoRepository.findByPlaca(placa)).thenReturn(Optional.empty());

        assertDoesNotThrow(
                () -> veiculoService.validateNewPlaca(placa)
        );
    }

}
