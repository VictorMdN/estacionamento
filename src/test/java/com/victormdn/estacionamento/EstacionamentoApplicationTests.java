package com.victormdn.estacionamento;

import com.victormdn.estacionamento.config.SwaggerConfig;
import com.victormdn.estacionamento.controller.*;
import com.victormdn.estacionamento.repository.EstabelecimentoRepository;
import com.victormdn.estacionamento.repository.EstadiaRepository;
import com.victormdn.estacionamento.repository.VeiculoRepository;
import com.victormdn.estacionamento.service.EstabelecimentoService;
import com.victormdn.estacionamento.service.EstadiaService;
import com.victormdn.estacionamento.service.RelatorioService;
import com.victormdn.estacionamento.service.VeiculoService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EstacionamentoApplicationTests {

	@Autowired
	VeiculoRepository veiculoRepository;

	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;

	@Autowired
	EstadiaRepository estadiaRepository;

	@Autowired
	VeiculoService veiculoService;

	@Autowired
	EstabelecimentoService estabelecimentoService;

	@Autowired
	EstadiaService estadiaService;

	@Autowired
	RelatorioService relatorioService;

	@Autowired
	VeiculoController veiculoController;

	@Autowired
	EstabelecimentoController estabelecimentoController;

	@Autowired
	EstadiaController estadiaController;

	@Autowired
	RelatorioController relatorioController;

	@Autowired
	LogController logController;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	Docket docket;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		assertThat(veiculoRepository).isNotNull();
		assertThat(estabelecimentoRepository).isNotNull();
		assertThat(estadiaRepository).isNotNull();
		assertThat(veiculoService).isNotNull();
		assertThat(estabelecimentoService).isNotNull();
		assertThat(estadiaService).isNotNull();
		assertThat(relatorioService).isNotNull();
		assertThat(veiculoController).isNotNull();
		assertThat(estabelecimentoController).isNotNull();
		assertThat(estadiaController).isNotNull();
		assertThat(relatorioController).isNotNull();
		assertThat(logController).isNotNull();
		assertThat(modelMapper).isNotNull();
		assertThat(docket).isNotNull();
		assertThat(passwordEncoder).isNotNull();
	}

}
