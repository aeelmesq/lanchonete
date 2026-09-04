# Lanchonete - Teste Técnico Salutem

Aplicação Web para gerenciamento de uma lanchonete, com cadastro de bebidas, ingredientes, hamburgueres e pedidos

## Funcionalidades

- Cadastro, edição, exclusão e pesquisa (por código ou descrição) das quatro entidades
- Montagem de hamburguer com seleção de ingredientes
- Pedido com hambúrgueres, bebidas, adicionais e observações
- **Lançamento de adicionais** (item 3.4) — apenas ingredientes habilitados como adicional
- **Cálculo automático do total** (item 3.5)
- Validação de campos obrigatórios e código duplicado (HTTP 400, 404 e 409)

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

Requer o Angular CLI(npm install -g @angular/cli). Frontend na URL http://localhost:4200
