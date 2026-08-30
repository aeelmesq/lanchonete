# Lanchonete - Teste Técnico Salutem

## Versões

- **Backend:** Java 21 + Spring Boot 4.1.1 (Maven)
- **Front-end:** Angular
- **Banco:** PostgreSQL 18 (Docker)

## Portas

| Serviço | Porta |
|---|---|
| API | 8080|
| PostgreSQL | 5433 |

## Como executar

### 1. Banco de dados
Na raiz do projeto:

    docker compose up -d

### 2. Backend

    cd backend
    ./mvnw spring-boot:run

