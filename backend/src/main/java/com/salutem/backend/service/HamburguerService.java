package com.salutem.backend.service;

import com.salutem.backend.entity.Hamburguer;
import com.salutem.backend.entity.Ingrediente;
import com.salutem.backend.exception.CodigoDuplicadoException;
import com.salutem.backend.exception.RecursoNaoEncontradoException;
import com.salutem.backend.repository.HamburguerRepository;
import com.salutem.backend.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HamburguerService {
    private final HamburguerRepository repository;
    private final IngredienteRepository ingredienteRepository;

    public HamburguerService(HamburguerRepository repository, IngredienteRepository ingredienteRepository)
    {
        this.repository = repository;
        this.ingredienteRepository = ingredienteRepository;
    }

    private List<Ingrediente> validarIngredientes(List<Ingrediente> ingredientes)
    {
        List<Ingrediente> validos = new ArrayList<>();
        for (Ingrediente ingrediente : ingredientes)
        {
            validos.add(ingredienteRepository.findById(ingrediente.getId()).orElseThrow(() -> new RecursoNaoEncontradoException("Ingrediente não encontrado")));
        }

        return validos;
    }

    public List<Hamburguer> listarTodos() {return repository.findAll();}

    public Hamburguer buscarPorId(Long id) {return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Hamburguer não encontrado"));}

    public Hamburguer criar(Hamburguer hamburguer)
    {
        if (repository.existsByCodigo(hamburguer.getCodigo()))
        {
            throw new CodigoDuplicadoException("Já existe um hamburguer com o código " + hamburguer.getCodigo());
        }

        hamburguer.setIngredientes(validarIngredientes(hamburguer.getIngredientes()));

        return repository.save(hamburguer);
    }

    public Hamburguer atualizar(Long id, Hamburguer dados)
    {
        Hamburguer hamburguer = buscarPorId(id);

        if (!dados.getCodigo().equals(hamburguer.getCodigo()) && repository.existsByCodigo(dados.getCodigo()))
        {
            throw new CodigoDuplicadoException("Já existe um hamburguer com o código " + dados.getCodigo());
        }

        hamburguer.setCodigo(dados.getCodigo());
        hamburguer.setDescricao(dados.getDescricao());
        hamburguer.setValor(dados.getValor());

        hamburguer.setIngredientes(validarIngredientes(dados.getIngredientes()));

        return repository.save(hamburguer);
    }

    public void deletar(Long id) { repository.delete(buscarPorId(id)); }

    public List<Hamburguer> pesquisar(String codigo, String descricao, String termo)
    {
        if (termo != null && !termo.isBlank())
        {
            return repository.findByCodigoContainingIgnoreCaseOrDescricaoContainingIgnoreCase(termo, termo);
        }

        if (codigo != null && !codigo.isBlank())
        {
            return repository.findByCodigo(codigo).map(List::of).orElse(List.of());
        }

        if (descricao != null && !descricao.isBlank())
        {
            return repository.findByDescricaoContainingIgnoreCase(descricao);
        }

        return repository.findAll();
    }
}
