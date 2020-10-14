package com.victormdn.estacionamento.service;

import com.victormdn.estacionamento.dto.EstadiaPublicDTO;
import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.model.Estadia;
import com.victormdn.estacionamento.model.Veiculo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EstadiaServiceTest {

    @Mock
    ModelMapper modelMapper;

    //@Autowired
    @InjectMocks
    EstadiaService estadiaService;

    /*@BeforeEach
    void initVeiculoService(){
        estadiaService = new EstadiaService();
    }

    @Test
    public void ComponentesInjetados_True(){
        assertThat(estadiaService).isNotNull();
        assertThat(modelMapper).isNotNull();
    }*/

    @Test
    public void estadiaToEstadiaPublicDTO_CamposPreenchidos_Equals(){
        Estadia estadia = Estadia.builder()
                .veiculo(Veiculo.builder().id(2L).build())
                .estabelecimento(Estabelecimento.builder().id(3L).build())
                .build();

        when(modelMapper.map(estadia, EstadiaPublicDTO.class)).thenReturn(new EstadiaPublicDTO());

        EstadiaPublicDTO estadiaPublicDTO = estadiaService.estadiaToEstadiaPublicDTO(estadia);

        assertThat(estadiaPublicDTO.getVeiculo()).isEqualTo(estadia.getVeiculo().getId());
        assertThat(estadiaPublicDTO.getEstabelecimento()).isEqualTo(estadia.getEstabelecimento().getId());
    }

}
