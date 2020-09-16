package com.victormdn.estacionamento.repository;

import com.victormdn.estacionamento.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    long countByEndereco(String endereco);

    long countByEnderecoAndId(String endereco, Long id);
}
