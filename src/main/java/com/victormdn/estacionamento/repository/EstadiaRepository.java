package com.victormdn.estacionamento.repository;

import com.victormdn.estacionamento.model.Estadia;
import com.victormdn.estacionamento.model.Tipo;
import com.victormdn.estacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadiaRepository extends JpaRepository<Estadia, Long> {

    @Query("SELECT e FROM Estadia e WHERE e.veiculo.id = :veiculo AND e.saida IS NULL")
    List<Estadia> estacionado(@Param("veiculo") Long veiculo);

    @Query("SELECT e FROM Estadia e WHERE e.estabelecimento.id = :id AND e.veiculo.tipo = :tipo AND e.saida IS NULL")
    List<Estadia> locados(@Param("id") Long id, @Param("tipo") Tipo tipo);
}
