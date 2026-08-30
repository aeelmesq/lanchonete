package com.salutem.backend.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroResposta(LocalDateTime timestamp, int status, String mensagem, Map<String, String> erros) {
}
