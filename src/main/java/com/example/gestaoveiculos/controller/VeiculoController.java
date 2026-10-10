package com.example.gestaoveiculos.controller;

import com.example.gestaoveiculos.Modelo;
import com.example.gestaoveiculos.Veiculo;
import com.example.gestaoveiculos.repository.ModeloRepository;
import com.example.gestaoveiculos.repository.VeiculoRepository;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;

    public VeiculoController(
            VeiculoRepository veiculoRepository,
            ModeloRepository modeloRepository) {

        this.veiculoRepository = veiculoRepository;
        this.modeloRepository = modeloRepository;
    }

    // Listar todos os veículos
    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    // Buscar um veículo pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(
            @PathVariable Long id) {

        return veiculoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtrar veículos por modelo, marca, status, ano e preço
    @GetMapping("/filtro")
    public ResponseEntity<?> filtrar(
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer anoFabricacao,
            @RequestParam(required = false) BigDecimal minimo,
            @RequestParam(required = false) BigDecimal maximo) {

        if (minimo != null && minimo.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest()
                    .body("O preço mínimo não pode ser negativo.");
        }

        if (maximo != null && maximo.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest()
                    .body("O preço máximo não pode ser negativo.");
        }

        if (minimo != null && maximo != null
                && minimo.compareTo(maximo) > 0) {

            return ResponseEntity.badRequest()
                    .body("O preço mínimo não pode ser maior que o máximo.");
        }

        List<Veiculo> veiculos = veiculoRepository.findAll();

        List<Veiculo> resultado = veiculos.stream()
                .filter(v -> modelo == null || modelo.isBlank()
                        || (v.getModelo() != null
                        && v.getModelo().getNome() != null
                        && v.getModelo().getNome()
                                .equalsIgnoreCase(modelo.trim())))

                .filter(v -> marca == null || marca.isBlank()
                        || (v.getModelo() != null
                        && v.getModelo().getMarca() != null
                        && v.getModelo().getMarca().getNome() != null
                        && v.getModelo().getMarca().getNome()
                                .equalsIgnoreCase(marca.trim())))

                .filter(v -> status == null || status.isBlank()
                        || (v.getStatus() != null
                        && v.getStatus().equalsIgnoreCase(status.trim())))

                .filter(v -> anoFabricacao == null
                        || anoFabricacao.equals(v.getAnoFabricacao()))

                .filter(v -> minimo == null
                        || (v.getPreco() != null
                        && v.getPreco().compareTo(minimo) >= 0))

                .filter(v -> maximo == null
                        || (v.getPreco() != null
                        && v.getPreco().compareTo(maximo) <= 0))

                .toList();

        return ResponseEntity.ok(resultado);
    }

    // Cadastrar um veículo
    @PostMapping
    public ResponseEntity<?> cadastrar(
            @Valid @RequestBody Veiculo veiculo) {

        if (veiculo.getModelo() == null
                || veiculo.getModelo().getId() == null) {

            return ResponseEntity.badRequest()
                    .body("É obrigatório informar o ID do modelo.");
        }

        Modelo modelo = modeloRepository.findById(
                veiculo.getModelo().getId()).orElse(null);

        if (modelo == null) {
            return ResponseEntity.badRequest()
                    .body("O modelo informado não existe.");
        }

        veiculo.setModelo(modelo);

        return ResponseEntity.ok(veiculoRepository.save(veiculo));
    }

    // Atualizar um veículo
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Veiculo dados) {

        return veiculoRepository.findById(id)
                .map(veiculo -> {

                    if (dados.getModelo() == null
                            || dados.getModelo().getId() == null) {

                        return ResponseEntity.badRequest()
                                .body("É obrigatório informar o ID do modelo.");
                    }

                    Modelo modelo = modeloRepository.findById(
                            dados.getModelo().getId()).orElse(null);

                    if (modelo == null) {
                        return ResponseEntity.badRequest()
                                .body("O modelo informado não existe.");
                    }

                    veiculo.setAnoFabricacao(dados.getAnoFabricacao());
                    veiculo.setCor(dados.getCor());
                    veiculo.setPreco(dados.getPreco());
                    veiculo.setQuilometragem(dados.getQuilometragem());
                    veiculo.setStatus(dados.getStatus());
                    veiculo.setModelo(modelo);

                    return ResponseEntity.ok(
                            veiculoRepository.save(veiculo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir um veículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        if (!veiculoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        veiculoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}