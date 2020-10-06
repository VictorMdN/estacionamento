package com.victormdn.estacionamento.config;

import com.victormdn.estacionamento.repository.EstabelecimentoRepository;
import com.victormdn.estacionamento.repository.VeiculoRepository;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;


    @Bean
    public ModelMapper modelMapper() {
        Condition<?, ?> skipLongWithNaN = context ->
                context.getMapping().getLastDestinationProperty().getType().equals(Long.class) ==
                context.getMapping().getSourceType().equals(Long.class);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true)
                .setPropertyCondition(skipLongWithNaN);
        return modelMapper;
    }

}
