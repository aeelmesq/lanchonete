package com.salutem.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta naoEncontrado(RecursoNaoEncontradoException ex)
    {
        return new ErroResposta(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(CodigoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta codigoDuplicado(CodigoDuplicadoException ex)
    {
        return new ErroResposta(LocalDateTime.now(), HttpStatus.CONFLICT.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta dadosInvalidos(MethodArgumentNotValidException ex)
    {
        Map<String, String> erros = new HashMap<>();

        for (FieldError erro : ex.getBindingResult().getFieldErrors())
        {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        return new ErroResposta(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Dados Inválidos", erros);
    }
}
