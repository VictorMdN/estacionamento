package com.victormdn.estacionamento.dto;

import com.victormdn.estacionamento.model.Tipo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EstabelecimentoRelatorioDTO extends EstabelecimentoPublicDTO{

    private Integer vagasCarroVazias;

    private Integer vagasMotoVazias;

    private Map<Tipo, List<EstadiaRelatorioDTO>> veiculos;
}
