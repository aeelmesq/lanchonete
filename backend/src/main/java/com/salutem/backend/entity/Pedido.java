package com.salutem.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {
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
    private LocalDateTime dataPedido;

    @ElementCollection
    private List<String> observacoes;

    @Column(nullable = false)
    @NotBlank
    private String clienteNome;

    @Column(nullable = false)
    @NotBlank
    private String clienteEndereco;

    @Column(nullable = false)
    @NotBlank
    private String clienteTelefone;

    @ManyToMany
    private List<Hamburguer> hamburgueres;

    @ManyToMany
    private List<Bebida> bebidas;

    @ManyToMany
    private List<Ingrediente> adicionais;

    public BigDecimal getTotal() {
        BigDecimal somaTotal = BigDecimal.ZERO;

        if (hamburgueres != null) {
            for (Hamburguer hamburguer : hamburgueres) {
                somaTotal = somaTotal.add(hamburguer.getValor());
            }
        }

        if (bebidas != null) {
            for (Bebida bebida : bebidas) {
                somaTotal = somaTotal.add(bebida.getPrecoUnitario());
            }
        }

        if (adicionais != null) {
            for (Ingrediente ingrediente : adicionais) {
                somaTotal = somaTotal.add(ingrediente.getPrecoUnitario());
            }
        }

        return somaTotal;
    }

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

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<String> getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(List<String> observacoes) {
        this.observacoes = observacoes;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEndereco() {
        return clienteEndereco;
    }

    public void setClienteEndereco(String clienteEndereco) {
        this.clienteEndereco = clienteEndereco;
    }

    public String getClienteTelefone() {
        return clienteTelefone;
    }

    public void setClienteTelefone(String clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }

    public List<Hamburguer> getHamburgueres() {
        return hamburgueres;
    }

    public void setHamburgueres(List<Hamburguer> hamburgueres) {
        this.hamburgueres = hamburgueres;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(List<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    public List<Ingrediente> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<Ingrediente> adicionais) {
        this.adicionais = adicionais;
    }
}
