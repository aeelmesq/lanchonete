package com.salutem.backend.controller;

import com.salutem.backend.entity.Ingrediente;
import com.salutem.backend.service.IngredienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    private final IngredienteService service;

    public IngredienteController(IngredienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ingrediente> pesquisar(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String termo
    ) {
        return service.pesquisar(codigo, descricao, termo);
    }

    @GetMapping("/{id}")
    public Ingrediente buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/adicionais")
    public List<Ingrediente> listarAdicionais() {
        return service.listarAdicionais();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingrediente criar(@Valid @RequestBody Ingrediente ingrediente) {
        return service.criar(ingrediente);
    }

    @PutMapping("/{id}")
    public Ingrediente atualizar(@PathVariable Long id, @Valid @RequestBody Ingrediente dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
