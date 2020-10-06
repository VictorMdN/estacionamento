package com.victormdn.estacionamento.repository;

import com.victormdn.estacionamento.model.Estabelecimento;
import com.victormdn.estacionamento.model.Estadia;
import com.victormdn.estacionamento.model.Tipo;
import com.victormdn.estacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadiaRepository extends JpaRepository<Estadia, Long> {

    List<Estadia> findByEstabelecimentoAndSaidaIsNull(Estabelecimento estabelecimento);

    List<Estadia> findByEstabelecimento(Estabelecimento estabelecimento);

    Optional<Estadia> findByVeiculoAndSaidaIsNull(Veiculo veiculo);

    Long countByEstabelecimentoIdAndVeiculoTipoAndSaidaIsNull(Long id, Tipo tipo);

}
