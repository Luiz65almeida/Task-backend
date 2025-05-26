# Task API Backend

Este projeto é uma API RESTful desenvolvida com **Spring Boot 3.5**, projetada para gerenciar tarefas com autenticação baseada em **JWT**, controle de usuários e permissões, e documentação via Swagger. Ideal para demonstrar boas práticas de arquitetura em backend, segurança, versionamento e testes automatizados.

## Funcionalidades

- Cadastro e autenticação de usuários com JWT
- Controle de acesso com papéis (Roles)
- CRUD completo de tarefas
- Filtro de requisições com JWT e Spring Security
- Documentação interativa com Swagger UI
- Estrutura preparada para testes (JUnit + Mockito)
- **Mapeamento automático entre DTOs e entidades com MapStruct**

## Tecnologias e Ferramentas

- Java 21
- Spring Boot 3.5
  - Spring Security
  - Spring Data JPA
  - Spring Web
- MySQL
- JWT (JSON Web Token)
- Swagger (Springdoc OpenAPI)
- MapStruct
- Maven
- Docker (opcional)

## Dependências e Bibliotecas Utilizadas

| Biblioteca                    | Descrição                                                 |
| ----------------------------- | --------------------------------------------------------- |
| **MySQL Connector**           | Driver JDBC para MySQL                                    |
| **Springdoc OpenAPI**         | Integração do Swagger/OpenAPI com Spring Boot             |
| **Spring Boot DevTools**      | Ferramentas de desenvolvimento (hot reload)               |
| **JJWT (API, Impl, Jackson)** | Autenticação com JSON Web Tokens                          |
| **MapStruct**                 | Mapeamento automático entre DTOs e entidades              |
| **Lombok**                    | Redução de boilerplate com anotações como @Getter/@Setter |
| **Mockito**                   | Framework de testes com mocks                             |
| **Jakarta Validation**        | API para validações (Bean Validation)                     |
| **Spring Data JPA**           | Integração com JPA e Hibernate                            |
| **Spring Security**           | Autenticação e controle de acesso                         |
| **Spring Web**                | Desenvolvimento de APIs REST                              |
| **Spring Boot Test**          | Framework de testes com JUnit e Mockito                   |
| **Spring Security Test**      | Testes específicos para segurança Spring                  |

## Uso de MapStruct

O projeto faz uso prático de **MapStruct** para automatizar o mapeamento entre entidades e DTOs, melhorando legibilidade e manutenção.

### Mappers criados:

- `UserMapper` - para `User ↔ UserDto`, `UserCreateDto`
- `TaskMapper` - para `Task ↔ TaskDto`, `TaskCreateDto`, `TaskUpdateDto`
- `RoleMapper` - para `Role ↔ RoleDto`

### Onde são usados:

- `AuthService` usa `UserMapper` para registrar usuários
- `UserService` usa `UserMapper` para retornar o perfil autenticado
- `TaskService` usa `TaskMapper` para criação, listagem e atualização de tarefas

## Estrutura de Pacotes

```
com.example.task_api_backend
│
├── controller         # Controladores REST (Auth, Task)
├── service            # Lógica de negócios (AuthService, TaskService, etc.)
├── repository         # Interfaces de acesso a dados
├── model              # Entidades JPA: Task, User, Role
├── dto                # Objetos de transferência de dados
├── mapper             # Mapeadores DTO ↔ Entidade (via MapStruct)
├── configuration      # SwaggerConfig e SecurityConfig
├── security           # JWT Provider e Filtro de Autenticação
├── exception          # Handler de exceções globais
└── TaskApiBackendApplication.java
```

## Autenticação JWT

O fluxo de autenticação se baseia em dois endpoints:

- `POST /api/auth/login`: autenticação e geração do token JWT
- `POST /api/auth/register`: cadastro de novo usuário

O token JWT deve ser enviado no cabeçalho `Authorization` das requisições protegidas:

```
Authorization: Bearer <token>
```

## Swagger

Acesse `http://localhost:8080/swagger-ui.html` após rodar a aplicação para explorar todos os endpoints.

## Configuração

### `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_api_backend
spring.datasource.username=usuario
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Docker (para banco de dados)

```bash
docker run -d     --name mysql-dev     -e MYSQL_ROOT_PASSWORD=root     -e MYSQL_DATABASE=task_api_backend     -e MYSQL_USER=usuario     -e MYSQL_PASSWORD=123456     -p 3306:3306     mysql:latest
```

## Melhorias Futuras

- Rate limiting e monitoramento com Spring Actuator
- Deploy automatizado com GitHub Actions + Docker

## Testes

Os testes implementados em `src/test/java/com/example/task_api_backend` têm o foco em:

- Services e lógica de negócio
- Validação dos DTOs
- Integração com o banco de dados

---

Desenvolvido por **Luiz Henrique Almeida**
