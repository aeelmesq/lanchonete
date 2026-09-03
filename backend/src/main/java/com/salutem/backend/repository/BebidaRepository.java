package com.salutem.backend.repository;

import com.salutem.backend.entity.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    Optional<Bebida> findByCodigo(String codigo);

    List<Bebida> findByDescricaoContainingIgnoreCase(String descricao);

    List<Bebida> findByCodigoContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String codigo, String descricao);

    boolean existsByCodigo(String codigo);
}
