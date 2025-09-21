#  Dev Back-end

![Java](https://img.shields.io/badge/Java-17-red?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven)
![Status](https://img.shields.io/badge/Status-Ativo-success?style=for-the-badge)

Projeto desenvolvido em **Spring Boot (Java 17)** como parte de um desafio técnico.  
O objetivo foi implementar duas APIs:

- **Problema 01 — Dashboard de Tickets**
- **Problema 02 — API de Receitas (integração com Forkify)**

---

##  Estrutura do Projeto

```bash
dev-backend/
├─ src/
│  └─ main/
│     ├─ java/com/alfa/devbackend/
│     │  ├─ tickets/      # Problema 01 — Tickets e Dashboard
│     │  └─ receitas/     # Problema 02 — Integração com Forkify
│     │
│     └─ resources/
│        ├─ application.yml   # Configurações do Spring Boot
│        ├─ schema.sql        # Estrutura inicial do banco H2
│        └─ data.sql          # Dados iniciais (clientes, módulos, tickets)
│
├─ pom.xml        # Dependências Maven
└─ README.md      # Documentação
```bash


 Tecnologias Utilizadas

Java 17

Spring Boot 3 (Web, Data JPA, Validation)

Banco H2 (in-memory) para persistência e testes

Lombok para reduzir boilerplate

Swagger / OpenAPI para documentação das APIs

 Como Rodar o Projeto
Pré-requisitos

Java 17+ instalado

Maven instalado ou usar o wrapper (mvnw)


Executando o projeto

# Windows
.\mvnw spring-boot:run

# Linux / macOS
./mvnw spring-boot:run


 Documentação da API

Após subir o projeto, os principais endpoints são:

Recurso	Descrição	URL
Swagger UI	Documentação interativa das APIs	http://localhost:8080/swagger-ui.html

Dashboard	Consulta de tickets por mês e ano	http://localhost:8080/api/v1/dashboard?month=3&year=2021

Recipes	Busca receitas na Forkify API	http://localhost:8080/api/v1/recipes?dish=pizza


 Banco de Dados (H2)

Banco: em memória, recriado a cada execução

Scripts: schema.sql e data.sql carregados automaticamente

Console H2: http://localhost:8080/h2-console

Credenciais:

JDBC URL: jdbc:h2:mem:alfa

Usuário: sa

Senha: (em branco)


 Endpoints Principais
Problema 01 — Dashboard de Tickets - Criar Ticket e Consultar Dashboard


Problema 02 — API de Receitas (Forkify) - Buscar receitas


 Referências

Spring Boot Reference Documentation: https://docs.spring.io/spring-boot/docs/current/reference/html/

Spring Data JPA Documentation: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

Jakarta Bean Validation: https://jakarta.ee/specifications/bean-validation/

Springdoc OpenAPI: https://springdoc.org/

H2 Database Documentation: https://www.h2database.com/html/main.html

Forkify API: https://forkify-api.herokuapp.com/

Autor

Ocante António.
