
# Desafio 03 - CRUD Clientes

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?logo=springboot&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven&logoColor=white) ![H2](https://img.shields.io/badge/H2-Database-1E5A96) ![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?logo=hibernate&logoColor=white)

Este projeto foi desenvolvido no capítulo 3 do módulo Spring Professional do curso DevSuperior.

### O projeto cumpre com as seguintes operações básicas de um CRUD:
- Busca paginada de recursos
- Busca de recurso por id
- Inserir novo recurso
- Atualizar recurso
- Deletar recurso

### Também tem como tratamento de exceções:
- Id não encontrado (para GET por id, PUT e DELETE), retornando código 404.
- Erro de validação, retornando código 422 e mensagens customizada para cada campo inválido. As

### Regras de validação são:
- Nome: não pode ser vazio
- Data de nascimento: não pode ser data futura

### Tecnologias e dependências


### Pré-requisitos

Para executar a aplicação, é necessário instalar:

- Java JDK 17
- Apache Maven 3.9 ou superior, caso o projeto não possua Maven Wrapper
- Postman, opcionalmente, para testar as requisições

O banco H2 é incorporado à aplicação e não precisa ser instalado separadamente.

### Dependências do projeto

| Dependência | Versão | Finalidade |
| :--- | :--- | :--- |
| Spring Boot | `4.1.1` | Configuração e execução da aplicação |
| Spring Web MVC | Gerenciada pelo Spring Boot `4.1.1` | Criação da API REST |
| Spring Data JPA | Gerenciada pelo Spring Boot `4.1.1` | Persistência e acesso aos dados |
| Spring Boot H2 Console | Gerenciada pelo Spring Boot `4.1.1` | Console web do banco H2 |
| H2 Database | Gerenciada pelo Spring Boot `4.1.1` | Banco de dados em memória |
| Jakarta Validation API | `3.0.2` | Definição das validações |
| Hibernate Validator | `8.0.0.CR2` | Implementação das validações |

## Coleção do Postman

O projeto possui uma coleção do Postman com as requisições necessárias para testar todas as operações da API.

A coleção está localizada em:

```text
test/api-postman/crud_clients.postman_collection
```
## API

A URL base utilizada nos exemplos é:

```http
http://localhost:8080
```

### Buscar cliente por ID

```http
GET /clients/{id}
```

| Parâmetro | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | `long` | **Obrigatório.** ID do cliente |

**Resposta de sucesso:** `200 OK`  
**Exceção:** `404 Not Found` — `ClientNotFoundException`

### Busca paginada de clientes com filtros

```http
GET /clients?page=0&size=10&sort=name,asc
```

| Parâmetro | Tipo | Descrição |
| :--- | :--- | :--- |
| `page` | `integer` | Opcional. Número da página |
| `size` | `integer` | Opcional. Quantidade de registros por página |
| `sort` | `string` | Opcional. Campo e direção da ordenação |

**Resposta de sucesso:** `200 OK`



### Cadastrar cliente

```http
POST /clients
```

Exemplo do corpo da requisição:

```json
{
  "name": "Cliente Exemplo",
  "cpf": "12345678900",
  "income": 3500.0,
  "birthDate": "1995-05-20",
  "children": 1
}
```

**Resposta de sucesso:** `200 OK`  
**Exceção:** `422 Unprocessable Entity` — dados inválidos

### Atualizar cliente

```http
PUT /clients/{id}
```

| Parâmetro | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | `long` | **Obrigatório.** ID do cliente |

O corpo da requisição deve seguir a mesma estrutura utilizada no cadastro.

**Resposta de sucesso:** `200 OK`  
**Exceções:**

- `404 Not Found` — `ClientNotFoundException`
- `422 Unprocessable Entity` — dados inválidos

### Excluir cliente

```http
DELETE /clients/{id}
```

| Parâmetro | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | `long` | **Obrigatório.** ID do cliente |

**Resposta de sucesso:** `204 No Content`  
**Exceção:** `404 Not Found` — `ClientNotFoundException`



## Carga inicial do banco de dados - SEED

O banco de dados é populado automaticamente pelo arquivo `import.sql` durante a inicialização da aplicação.

O arquivo insere dez clientes fictícios, com diferentes nomes, CPFs, rendas, datas de nascimento e quantidades de filhos. Esses dados servem como massa de teste para validar a paginação, a busca por ID e as demais operações do CRUD.
## Como executar

1. Clone o repositório:

```bash
    git clone https://github.com/RonaldoGR/desafio-03-devsuperior.git
```

2. Entre na pasta do projeto:

```bash
cd desafio-03-devsuperior
```

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

Caso esteja utilizando uma IDE, como o IntelliJ IDEA, abra o projeto e execute a aplicação pelo botão **Run**.

4. Acesse a API em:

```http
http://localhost:8080/clients
```
## Autor

- [@Ronaldo Gandra Rocha](https://www.github.com/RonaldoGR)