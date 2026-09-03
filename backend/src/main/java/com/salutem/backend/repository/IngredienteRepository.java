package com.salutem.backend.repository;

import com.salutem.backend.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    Optional<Ingrediente> findByCodigo(String codigo);

    List<Ingrediente> findByDescricaoContainingIgnoreCase(String descricao);

    List<Ingrediente> findByCodigoContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String codigo, String descricao);

    boolean existsByCodigo(String codigo);

    List<Ingrediente> findByPodeSerAdicionalTrue();
}
