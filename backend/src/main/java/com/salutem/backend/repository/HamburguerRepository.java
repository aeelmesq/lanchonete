package com.salutem.backend.repository;

import com.salutem.backend.entity.Hamburguer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HamburguerRepository extends JpaRepository<Hamburguer, Long> {
    Optional<Hamburguer> findByCodigo(String codigo);

    List<Hamburguer> findByDescricaoContainingIgnoreCase(String descricao);

    List<Hamburguer> findByCodigoContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String codigo, String descricao);

    boolean existsByCodigo(String codigo);
}
