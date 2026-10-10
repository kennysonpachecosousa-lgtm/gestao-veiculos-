package com.example.gestaoveiculos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A cor é obrigatória")
    private String cor;

    @NotNull(message = "O ano de fabricação é obrigatório")
    @Min(value = 1900, message = "O ano deve ser válido")
    private Integer anoFabricacao;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.0", message = "O preço não pode ser negativo")
    private BigDecimal preco;

    @NotNull(message = "A quilometragem é obrigatória")
    @Min(value = 0, message = "A quilometragem não pode ser negativa")
    private Integer quilometragem;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @ManyToOne
    private Modelo modelo;

    public Veiculo() {
    }

    public Long getId() {
        return id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}