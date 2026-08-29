package com.salutem.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
public class Bebida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String codigo;

    @Column(nullable = false)
    @NotBlank
    private String descricao;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    @NotNull
    private Boolean contemAcucar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Boolean getContemAcucar() {
        return contemAcucar;
    }

    public void setContemAcucar(Boolean contemAcucar) {
        this.contemAcucar = contemAcucar;
    }
}
