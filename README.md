# Sistema Automotivo — Gestão de Estoque de Veículos

## Sobre o projeto

Este projeto consiste em uma API REST desenvolvida em Java com Spring Boot para gerenciar o estoque de veículos de uma loja automotiva. O sistema permite cadastrar, consultar, atualizar e excluir veículos, além de realizar pesquisas utilizando filtros.

## Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Maven
* Postman
* Visual Studio Code

## Funcionalidades

### Gestão de marcas

* Cadastrar marcas.
* Listar todas as marcas.
* Consultar uma marca por ID.
* Atualizar uma marca.
* Excluir uma marca.

### Gestão de modelos

* Cadastrar modelos vinculados a marcas.
* Listar todos os modelos.
* Consultar um modelo por ID.
* Atualizar modelos.
* Excluir modelos.

### Gestão de veículos

* Cadastrar veículos vinculados a modelos.
* Listar veículos cadastrados.
* Consultar veículos por ID.
* Atualizar informações dos veículos.
* Excluir veículos.
* Filtrar por modelo, marca, status, ano de fabricação e faixa de preço.
* Validar campos obrigatórios e valores informados.

## Estrutura do projeto

```text
gestao-veiculos/
├── src/
│   ├── main/
│   │   ├── java/com/example/gestaoveiculos/
│   │   │   ├── controller/
│   │   │   ├── repository/
│   │   │   ├── Marca.java
│   │   │   ├── Modelo.java
│   │   │   ├── Veiculo.java
│   │   │   └── GestaoVeiculosApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Configuração do banco de dados

O projeto utiliza o MySQL com um banco de dados chamado `gestao_veiculos`.

Antes de executar a aplicação, crie o banco de dados:

```sql
CREATE DATABASE gestao_veiculos;
```

Configure as credenciais do banco no arquivo `application.properties`. A senha deve ser fornecida por uma variável de ambiente chamada `DB_PASSWORD`, sem ser incluída diretamente no código.

## Como executar

1. Instale o Java 17 ou uma versão compatível com a configuração do projeto.
2. Instale e inicie o MySQL.
3. Configure a conexão com o banco de dados.
4. Abra o terminal na pasta que contém o arquivo `pom.xml`.
5. Execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A API será disponibilizada localmente na porta `8080`.

## Endpoints principais

| Método | Endpoint           | Descrição           |
| ------ | ------------------ | ------------------- |
| GET    | `/marcas`          | Lista as marcas     |
| GET    | `/marcas/{id}`     | Consulta uma marca  |
| POST   | `/marcas`          | Cadastra uma marca  |
| PUT    | `/marcas/{id}`     | Atualiza uma marca  |
| DELETE | `/marcas/{id}`     | Exclui uma marca    |
| GET    | `/modelos`         | Lista os modelos    |
| POST   | `/modelos`         | Cadastra um modelo  |
| GET    | `/veiculos`        | Lista os veículos   |
| GET    | `/veiculos/{id}`   | Consulta um veículo |
| POST   | `/veiculos`        | Cadastra um veículo |
| PUT    | `/veiculos/{id}`   | Atualiza um veículo |
| DELETE | `/veiculos/{id}`   | Exclui um veículo   |
| GET    | `/veiculos/filtro` | Filtra veículos     |

Os endpoints de modelos também possuem operações de consulta por ID, atualização e exclusão.

## Testes

A API foi testada com o Postman. Foram verificadas a consulta de veículos, a comunicação com o banco de dados e a rejeição de um cadastro com a cor obrigatória vazia.

## Objetivo acadêmico

O projeto foi desenvolvido com o objetivo de aplicar conceitos de programação orientada a objetos, APIs REST, persistência de dados relacionais, validação de informações e operações CRUD.

## Autor

Projeto acadêmico desenvolvido para a disciplina de desenvolvimento de sistemas.
