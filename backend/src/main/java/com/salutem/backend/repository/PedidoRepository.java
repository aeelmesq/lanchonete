package com.salutem.backend.repository;

import com.salutem.backend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByCodigo(String codigo);

    List<Pedido> findByDescricaoContainingIgnoreCase(String descricao);

    List<Pedido> findByCodigoContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String codigo, String descricao);

    boolean existsByCodigo(String codigo);
}
