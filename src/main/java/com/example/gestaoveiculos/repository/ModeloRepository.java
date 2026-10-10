package com.example.gestaoveiculos.repository;

import com.example.gestaoveiculos.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}