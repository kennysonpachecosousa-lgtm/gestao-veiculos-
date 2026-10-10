package com.example.gestaoveiculos.controller;

import com.example.gestaoveiculos.Marca;
import com.example.gestaoveiculos.repository.MarcaRepository;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
@CrossOrigin(origins = "*")
public class MarcaController {

    private final MarcaRepository marcaRepository;

    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    // Listar todas as marcas
    @GetMapping
    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    // Buscar uma marca pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        return marcaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cadastrar uma nova marca
    @PostMapping
    public Marca cadastrar(@Valid @RequestBody Marca marca) {
        return marcaRepository.save(marca);
    }

    // Atualizar uma marca existente
    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Marca dados) {

        return marcaRepository.findById(id)
                .map(marca -> {
                    marca.setNome(dados.getNome());
                    return ResponseEntity.ok(marcaRepository.save(marca));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir uma marca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!marcaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        marcaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}