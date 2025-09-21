#  Dev Back-end

Projeto desenvolvido em **Spring Boot (Java 17)** como parte de um desafio técnico.  
O objetivo foi implementar duas APIs distintas:

- **Problema 01 — Dashboard de Tickets**  
- **Problema 02 — API de Receitas (integração com Forkify)**  

---

##  Estrutura do Projeto



dev-backend/
├─ src/main/java/com/alfa/devbackend
│ ├─ tickets/ # Problema 01 — Tickets e Dashboard
│ └─ receitas/ # Problema 02 — Integração com Forkify
├─ src/main/resources/
│ ├─ application.yml # Configurações do Spring Boot
│ ├─ schema.sql # Estrutura inicial do banco H2
│ └─ data.sql # Dados iniciais (clientes, módulos, tickets)
├─ pom.xml # Dependências Maven
└─ README.md # Documentação


---

## 🛠️ Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 3** (Spring Web, Spring Data JPA, Validation)  
- **Banco H2 (in-memory)** para persistência e testes  
- **Lombok** para reduzir boilerplate (getters/setters)  
- **Swagger / OpenAPI** para documentação das APIs  

---

##  Como Rodar o Projeto

### Pré-requisitos
- **Java 17+** instalado  
- **Maven** (ou usar o wrapper `mvnw`)  

### Executando
No terminal:

# Windows
.\mvnw spring-boot:run

# Linux/macOS
./mvnw spring-boot:run


 Documentação da API
URLS:
Usar as URLs corretas (mais rápido)

Swagger: http://localhost:8080/swagger-ui.html

Dashboard: http://localhost:8080/api/v1/dashboard?month=3&year=2021

Recipes: http://localhost:8080/api/v1/recipes?dish=pizza
---------------------------------------------------------
---------------------------------------------------------

Swagger UI disponível em:
 http://localhost:8080/swagger-ui.html

 Banco de Dados

Banco em memória (H2)

Carregado automaticamente com schema.sql e data.sql

Acesso ao console H2:
http://localhost:8080/h2-console/login.jsp?jsessionid=77952cdecc126b24c4e130a43500de20

JDBC URL: jdbc:h2:mem:alfa

Usuário: sa

Senha: (em branco)

<img width="605" height="280" alt="image" src="https://github.com/user-attachments/assets/2eaa42e8-ed15-460d-abae-22b8c44450ec" />

<img width="661" height="469" alt="image" src="https://github.com/user-attachments/assets/73983092-0f3f-4942-a995-4721e7d5321f" />


 Problema 01 — Dashboard de Tickets
Endpoints

Criar Ticket

POST /api/v1/tickets


Exemplo de payload:

{
  "title": "Erro no faturamento",
  "clientId": 1,
  "moduleId": 2,
  "openingDate": "2021-03-31",
  "closingDate": "2021-04-01"
}


Consultar Dashboard

GET /api/v1/dashboard?month=3&year=2021  <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/d848382e-af3b-4939-8331-efff310cae8f" />


Retorna:

Exemplo de resposta JSON do Dashboard:

<!-- imagem do navegador/Swagger mostrando o JSON -->
<img width="558" height="466" alt="image" src="https://github.com/user-attachments/assets/c05e35f6-c974-40e1-83bc-8e52e7ce3267" />


 Exemplo de criação de Ticket via Swagger:
<!-- aqui imagem criando um novo ticket -->
<img width="557" height="464" alt="image" src="https://github.com/user-attachments/assets/2629a27f-a7c4-4b55-93a3-a15a65d4497d" />


 Problema 02 — API de Receitas (Forkify)
Endpoint

Buscar receitas

GET /api/v1/recipes?dish=pizza


Exemplo de resposta:
Testes Realizados

 Exemplo de busca por “pizza”:
<!-- aqui imagem com outro teste -->
<img width="461" height="457" alt="image" src="https://github.com/user-attachments/assets/f0801549-41de-4465-85f4-365d88340fa5" />

📷 Exemplo de busca por outro prato (ex: pasta):

<!-- aqui imagem com outro teste -->
<img width="393" height="457" alt="image" src="https://github.com/user-attachments/assets/048a4492-24f3-41da-8b6e-c410023e11bb" />

## Conclusão

O projeto atende aos dois problemas propostos.

O uso do banco em memória + scripts facilita a validação imediata.

A documentação via Swagger garante praticidade nos testes.

# <------------------------------->
---

##  Referências

###  Documentação Oficial
- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)  
- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
- [Spring Validation (Jakarta Bean Validation)](https://jakarta.ee/specifications/bean-validation/)  
- [Springdoc OpenAPI (Swagger para Spring Boot)](https://springdoc.org/)  
- [H2 Database Engine Documentation](https://www.h2database.com/html/main.html)  
- [Lombok Project](https://projectlombok.org/)  
- [Forkify API (receitas)](https://forkify-api.herokuapp.com/)  

---

###  Comunidades e Fóruns
- [Stack Overflow - Spring Boot tag](https://stackoverflow.com/questions/tagged/spring-boot)  
- [Reddit r/java](https://www.reddit.com/r/java/)  
- [Reddit r/spring](https://www.reddit.com/r/spring/)  
- [Dev.to - Spring Boot Articles](https://dev.to/t/springboot)  

---

###  Tutoriais e Materiais Complementares
- [Baeldung - Spring Boot Guides](https://www.baeldung.com/spring-boot)  
- [Spring Initializr (gerador de projetos)](https://start.spring.io/)  
- [Reflectoring - Spring Boot Articles](https://reflectoring.io/tutorials/spring-boot/)  
- [DigitalOcean - Spring Boot Tutorials](https://www.digitalocean.com/community/tags/spring-boot)  

  <------------------------------->


Repositório público: GitHub - dev-backend


 Autor: Ocante António

---


