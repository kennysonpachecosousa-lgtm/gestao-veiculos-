package com.example.gestaoveiculos.controller;

import com.example.gestaoveiculos.Marca;
import com.example.gestaoveiculos.Modelo;
import com.example.gestaoveiculos.repository.MarcaRepository;
import com.example.gestaoveiculos.repository.ModeloRepository;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelos")
@CrossOrigin(origins = "*")
public class ModeloController {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;

    public ModeloController(
            ModeloRepository modeloRepository,
            MarcaRepository marcaRepository) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }

    // Listar todos os modelos
    @GetMapping
    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    // Buscar um modelo pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Modelo> buscarPorId(
            @PathVariable Long id) {

        return modeloRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cadastrar um novo modelo
    @PostMapping
    public ResponseEntity<?> cadastrar(
            @Valid @RequestBody Modelo modelo) {

        if (modelo.getMarca() == null
                || modelo.getMarca().getId() == null) {
            return ResponseEntity.badRequest()
                    .body("É obrigatório informar o ID da marca.");
        }

        Marca marca = marcaRepository.findById(
                modelo.getMarca().getId()).orElse(null);

        if (marca == null) {
            return ResponseEntity.badRequest()
                    .body("A marca informada não existe.");
        }

        modelo.setMarca(marca);

        return ResponseEntity.ok(modeloRepository.save(modelo));
    }

    // Atualizar um modelo existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Modelo dados) {

        return modeloRepository.findById(id)
                .map(modelo -> {

                    if (dados.getMarca() == null
                            || dados.getMarca().getId() == null) {
                        return ResponseEntity.badRequest()
                                .body("É obrigatório informar o ID da marca.");
                    }

                    Marca marca = marcaRepository.findById(
                            dados.getMarca().getId()).orElse(null);

                    if (marca == null) {
                        return ResponseEntity.badRequest()
                                .body("A marca informada não existe.");
                    }

                    modelo.setNome(dados.getNome());
                    modelo.setMarca(marca);

                    return ResponseEntity.ok(
                            modeloRepository.save(modelo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir um modelo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        if (!modeloRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        modeloRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}