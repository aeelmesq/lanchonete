package com.salutem.backend.service;

import com.salutem.backend.entity.Bebida;
import com.salutem.backend.exception.CodigoDuplicadoException;
import com.salutem.backend.exception.RecursoNaoEncontradoException;
import com.salutem.backend.repository.BebidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BebidaService {
    private final BebidaRepository repository;

    public BebidaService(BebidaRepository repository) {
        this.repository = repository;
    }

    public List<Bebida> listarTodos() {
        return repository.findAll();
    }

    public Bebida buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Bebida não encontrada"));
    }

    public Bebida criar(Bebida bebida) {
        if (repository.existsByCodigo(bebida.getCodigo())) {
            throw new CodigoDuplicadoException("Já existe bebida com o código " + bebida.getCodigo());
        }

        return repository.save(bebida);
    }

    public Bebida atualizar(Long id, Bebida dados) {
        Bebida bebida = buscarPorId(id);

        if (!dados.getCodigo().equals(bebida.getCodigo()) && repository.existsByCodigo(dados.getCodigo())) {
            throw new CodigoDuplicadoException("Já existe uma bebida com o código " + dados.getCodigo());
        }

        bebida.setCodigo(dados.getCodigo());
        bebida.setDescricao(dados.getDescricao());
        bebida.setPrecoUnitario(dados.getPrecoUnitario());
        bebida.setContemAcucar(dados.getContemAcucar());

        return repository.save(bebida);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public List<Bebida> pesquisar(String codigo, String descricao, String termo) {
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
}
