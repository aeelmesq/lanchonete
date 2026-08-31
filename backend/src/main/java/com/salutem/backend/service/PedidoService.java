package com.salutem.backend.service;

import com.salutem.backend.entity.Bebida;
import com.salutem.backend.entity.Hamburguer;
import com.salutem.backend.entity.Ingrediente;
import com.salutem.backend.entity.Pedido;
import com.salutem.backend.exception.CodigoDuplicadoException;
import com.salutem.backend.exception.RecursoNaoEncontradoException;
import com.salutem.backend.repository.BebidaRepository;
import com.salutem.backend.repository.HamburguerRepository;
import com.salutem.backend.repository.IngredienteRepository;
import com.salutem.backend.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository repository;
    private final HamburguerRepository hamburguerRepository;
    private final BebidaRepository bebidaRepository;
    private final IngredienteRepository ingredienteRepository;

    private List<Hamburguer> validarHamburgueres(List<Hamburguer> hamburgueres)
    {
        if (hamburgueres == null)
        {
            return new ArrayList<>();
        }

        List<Hamburguer> validos = new ArrayList<>();
        for (Hamburguer hamburguer : hamburgueres)
        {
            validos.add(hamburguerRepository.findById(hamburguer.getId()).orElseThrow(() -> new RecursoNaoEncontradoException("Hamburguer não encontrado")));
        }

        return validos;
    }

    private List<Bebida> validarBebidas(List<Bebida> bebidas)
    {
        if (bebidas == null)
        {
            return new ArrayList<>();
        }

        List<Bebida> validos = new ArrayList<>();
        for (Bebida bebida : bebidas)
        {
            validos.add(bebidaRepository.findById(bebida.getId()).orElseThrow(() -> new RecursoNaoEncontradoException("Bebida não encontrada")));
        }

        return validos;
    }

    private List<Ingrediente> validarAdicionais(List<Ingrediente> ingredientes)
    {
        if (ingredientes == null)
        {
            return new ArrayList<>();
        }

        List<Ingrediente> validos = new ArrayList<>();
        for (Ingrediente ingrediente : ingredientes)
        {
            validos.add(ingredienteRepository.findById(ingrediente.getId()).orElseThrow(() -> new RecursoNaoEncontradoException("Ingrediente não encontrado")));
        }

        return validos;
    }

    public PedidoService(PedidoRepository repository, HamburguerRepository hamburguerRepository, BebidaRepository bebidaRepository, IngredienteRepository ingredienteRepository)
    {
        this.repository = repository;
        this.hamburguerRepository = hamburguerRepository;
        this.bebidaRepository = bebidaRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Pedido> listarTodos() {return repository.findAll();}

    public Pedido buscarPorId(Long id) {return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));}

    public Pedido criar(Pedido pedido) {
        if (repository.existsByCodigo(pedido.getCodigo()))
        {
            throw new CodigoDuplicadoException("Já existe um pedido com o código " + pedido.getCodigo());
        }

        pedido.setDataPedido(LocalDateTime.now());

        pedido.setHamburgueres(validarHamburgueres(pedido.getHamburgueres()));
        pedido.setBebidas(validarBebidas(pedido.getBebidas()));
        pedido.setAdicionais(validarAdicionais(pedido.getAdicionais()));

        return repository.save(pedido);
    }

    public Pedido atualizar(Long id, Pedido dados)
    {
        Pedido pedido = buscarPorId(id);

        if (!dados.getCodigo().equals(pedido.getCodigo()) && repository.existsByCodigo(dados.getCodigo()))
        {
            throw new CodigoDuplicadoException("Já existe um pediddo com o código " + dados.getCodigo());
        }

        pedido.setCodigo(dados.getCodigo());
        pedido.setDescricao(dados.getDescricao());
        pedido.setObservacoes(dados.getObservacoes());

        pedido.setClienteNome(dados.getClienteNome());
        pedido.setClienteEndereco(dados.getClienteEndereco());
        pedido.setClienteTelefone(dados.getClienteTelefone());

        pedido.setHamburgueres(validarHamburgueres(dados.getHamburgueres()));
        pedido.setBebidas(validarBebidas(dados.getBebidas()));
        pedido.setAdicionais(validarAdicionais(dados.getAdicionais()));

        return repository.save(pedido);
    }

    public void deletar(Long id) { repository.delete(buscarPorId(id)); }

    public List<Pedido> pesquisar(String codigo, String descricao)
    {
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
