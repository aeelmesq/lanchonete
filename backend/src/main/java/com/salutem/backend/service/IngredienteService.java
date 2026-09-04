package com.salutem.backend.service;

import com.salutem.backend.entity.Ingrediente;
import com.salutem.backend.exception.CodigoDuplicadoException;
import com.salutem.backend.exception.RecursoNaoEncontradoException;
import com.salutem.backend.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {
    private final IngredienteRepository repository;

    public IngredienteService(IngredienteRepository repository) {
        this.repository = repository;
    }

    public List<Ingrediente> listarTodos() {
        return repository.findAll();
    }

    public Ingrediente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Ingrediente não encontrado"));
    }

    public Ingrediente criar(Ingrediente ingrediente) {
        if (repository.existsByCodigo(ingrediente.getCodigo())) {
            throw new CodigoDuplicadoException("Já existe um ingrediente com o código " + ingrediente.getCodigo());
        }

        return repository.save(ingrediente);
    }

    public Ingrediente atualizar(Long id, Ingrediente dados) {
        Ingrediente ingrediente = buscarPorId(id);

        if (!dados.getCodigo().equals(ingrediente.getCodigo()) && repository.existsByCodigo(dados.getCodigo())) {
            throw new CodigoDuplicadoException("Já existe um ingrediente com o código " + dados.getCodigo());
        }

        ingrediente.setCodigo(dados.getCodigo());
        ingrediente.setDescricao(dados.getDescricao());
        ingrediente.setPrecoUnitario(dados.getPrecoUnitario());
        ingrediente.setPodeSerAdicional(dados.getPodeSerAdicional());

        return repository.save(ingrediente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public List<Ingrediente> pesquisar(String codigo, String descricao, String termo) {
        if (termo != null && !termo.isBlank()) {
            return repository.findByCodigoContainingIgnoreCaseOrDescricaoContainingIgnoreCase(termo, termo);
        }

        if (codigo != null && !codigo.isBlank()) {
            return repository.findByCodigo(codigo).map(List::of).orElse(List.of());
        }

        if (descricao != null && !descricao.isBlank()) {
            return repository.findByDescricaoContainingIgnoreCase(descricao);
        }

        return repository.findAll();
    }

    public List<Ingrediente> listarAdicionais() {
        return repository.findByPodeSerAdicionalTrue();
    }
}
