# Lanchonete - Teste Técnico Salutem

## Versões

- **Backend:** Java 21 + Spring Boot 4.1.1 (Maven)
- **Frontend:** Angular 22 + Node 24
- **Banco:** PostgreSQL 18 (Docker)

## Portas

| Serviço | Porta |
|---|---|
| API | 8080 |
| Frontend | 4200 |
| PostgreSQL | 5433 |

## Como executar

### 1. Banco de dados
Na raiz do projeto:

    docker compose up -d

O Postgres sobe na porta 5433 para evitar conflito com porta padrão

### 2. Backend

    cd backend
    ./mvnw spring-boot:run

API na URL http://localhost:8080

### 3. Frontend

    cd frontend
    npm install
    ng serve

Frontend na URL http://localhost:4200