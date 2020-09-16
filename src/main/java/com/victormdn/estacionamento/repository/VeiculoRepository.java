package com.victormdn.estacionamento.repository;

import com.victormdn.estacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    long countByPlaca(String placa);

    long countByPlacaAndId(String placa, Long id);

    @Query("SELECT v FROM Veiculo v WHERE LOWER(v.placa) = LOWER(:placa) AND v.id <> :id")
    public List<Veiculo> findPlaca(@Param("placa") String placa, @Param("id") Long id);
}
