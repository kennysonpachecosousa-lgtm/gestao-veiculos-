package com.example.gestaoveiculos.repository;

import com.example.gestaoveiculos.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}