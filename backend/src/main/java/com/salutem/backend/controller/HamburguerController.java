package com.salutem.backend.controller;

import com.salutem.backend.entity.Hamburguer;
import com.salutem.backend.service.HamburguerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamburgueres")
public class HamburguerController {
    private final HamburguerService service;

    public HamburguerController(HamburguerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Hamburguer> pesquisar(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String termo
    ) {
        return service.pesquisar(codigo, descricao, termo);
    }

    @GetMapping("/{id}")
    public Hamburguer buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hamburguer criar(@Valid @RequestBody Hamburguer hamburguer) {
        return service.criar(hamburguer);
    }

    @PutMapping("/{id}")
    public Hamburguer atualizar(@PathVariable Long id, @Valid @RequestBody Hamburguer dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
