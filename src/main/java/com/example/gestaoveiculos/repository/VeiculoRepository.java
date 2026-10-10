package com.example.gestaoveiculos.repository;

import com.example.gestaoveiculos.Veiculo;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByModeloNomeContainingIgnoreCase(String modelo);

    List<Veiculo> findByModeloMarcaNomeContainingIgnoreCase(String marca);

    List<Veiculo> findByStatusIgnoreCase(String status);

    List<Veiculo> findByAnoFabricacao(Integer anoFabricacao);

    @Query("SELECT v FROM Veiculo v WHERE v.preco BETWEEN :minimo AND :maximo")
    List<Veiculo> filtrarPorFaixaDePreco(
            @Param("minimo") BigDecimal minimo,
            @Param("maximo") BigDecimal maximo
    );
}