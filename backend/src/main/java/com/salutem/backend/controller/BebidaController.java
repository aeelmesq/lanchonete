package com.salutem.backend.controller;

import com.salutem.backend.entity.Bebida;
import com.salutem.backend.service.BebidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bebidas")
public class BebidaController {
    private final BebidaService service;

    public BebidaController(BebidaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bebida> pesquisar(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String termo) {
        return service.pesquisar(codigo, descricao, termo);
    }

    @GetMapping("/{id}")
    public Bebida buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bebida criar(@Valid @RequestBody Bebida bebida) {
        return service.criar(bebida);
    }

    @PutMapping("/{id}")
    public Bebida atualizar(@PathVariable Long id, @Valid @RequestBody Bebida dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable() Long id) {
        service.deletar(id);
    }
}
