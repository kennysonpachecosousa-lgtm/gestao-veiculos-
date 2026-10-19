# 🚗 Sistema Automotivo — Gestão de Estoque de Veículos

API REST desenvolvida em Java com Spring Boot para o gerenciamento de estoque de veículos. O sistema permite cadastrar, consultar, atualizar e excluir marcas, modelos e veículos, além de realizar consultas com filtros específicos.

## 📌 Sobre o projeto

O Sistema Automotivo foi desenvolvido como projeto acadêmico para praticar desenvolvimento de APIs REST, persistência de dados relacionais, validação de informações e integração entre uma aplicação Java e um banco de dados MySQL.

A aplicação organiza os dados em três entidades principais: `Marca`, `Modelo` e `Veiculo`.

## ✨ Funcionalidades

* **Gestão de marcas:** cadastro, consulta, atualização e exclusão.
* **Gestão de modelos:** cadastro e gerenciamento de modelos associados às marcas.
* **Gestão de veículos:** cadastro, consulta, atualização e exclusão de veículos.
* **Filtros de pesquisa:** consulta de veículos por marca, modelo, status, ano e faixa de preço.
* **Validação de dados:** verificação de campos obrigatórios e valores informados.
* **Relacionamentos:** associação de veículos aos seus modelos e de modelos às marcas.
* **Respostas HTTP:** utilização de códigos de status para indicar o resultado das operações.
* **Persistência de dados:** armazenamento das informações em banco de dados MySQL.

## 🛠️ Tecnologias utilizadas

| Tecnologia      | Finalidade                                 |
| --------------- | ------------------------------------------ |
| Java 17         | Linguagem de programação                   |
| Spring Boot     | Desenvolvimento da aplicação               |
| Spring Data JPA | Persistência e acesso aos dados            |
| Maven           | Gerenciamento de dependências e compilação |
| MySQL           | Banco de dados relacional                  |
| MySQL Workbench | Administração e consulta do banco          |
| Postman         | Testes manuais dos endpoints REST          |
| Git e GitHub    | Versionamento e hospedagem do código       |

## 🏗️ Estrutura do projeto

A aplicação está organizada em componentes responsáveis pelas entidades, pelo acesso aos dados e pelas requisições HTTP.

```text
gestao-veiculos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── .../
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       └── repository/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── application.example.properties
```

*Observação: os nomes e os caminhos dos pacotes devem corresponder à estrutura real do código-fonte.*

## ⚙️ Pré-requisitos

Antes de executar o projeto, instale ou configure:

* Java JDK 17;
* MySQL Server;
* Maven, caso prefira utilizá-lo instalado separadamente;
* Git, para clonar o repositório.

## 🚀 Como instalar e executar

### 1. Clonar o repositório

```bash
git clone https://github.com/kennysonpachecosousa-lgtm/gestao-veiculos-.git
cd gestao-veiculos-
```

### 2. Criar o banco de dados

No MySQL Workbench, execute:

```sql
CREATE DATABASE gestao_veiculos;
```

Caso o banco já exista, não é necessário criá-lo novamente.

### 3. Configurar a conexão

Configure a aplicação para acessar o MySQL. O projeto utiliza a variável de ambiente `DB_PASSWORD` para fornecer a senha do banco sem deixá-la escrita diretamente no código.

No PowerShell, dentro do terminal que será utilizado para executar a aplicação:

```powershell
$senha = Read-Host "Digite a senha do MySQL" -AsSecureString
$env:DB_PASSWORD = [System.Net.NetworkCredential]::new("", $senha).Password
```

Confira também se o usuário, o endereço, a porta e o nome do banco estão corretos nas configurações da aplicação.

A configuração de exemplo deve servir como referência, sem conter senhas reais.

### 4. Compilar o projeto

No Windows, execute:

```powershell
.\mvnw.cmd clean compile
```

### 5. Executar a aplicação

```powershell
.\mvnw.cmd spring-boot:run
```

Por padrão, a aplicação utiliza a porta `8080`.

Com o servidor em execução, os endpoints podem ser acessados pelo Postman ou por outra ferramenta capaz de enviar requisições HTTP.

### 6. Executar os testes automatizados

Em outro terminal, configure também a variável `DB_PASSWORD`, caso os testes dependam da conexão com o MySQL, e execute:

```powershell
.\mvnw.cmd test
```

O resultado deve ser conferido no terminal. A execução dos testes não deve ser considerada bem-sucedida sem a confirmação de que o Maven terminou com `BUILD SUCCESS`.

## 🔗 Endpoints da API

A API disponibiliza operações REST para as entidades `Marca`, `Modelo` e `Veiculo`.

| Método HTTP | Endpoint         | Operação           |
| ----------- | ---------------- | ------------------ |
| GET         | `/marcas`        | Consultar marcas   |
| POST        | `/marcas`        | Cadastrar marca    |
| PUT         | `/marcas/{id}`   | Atualizar marca    |
| DELETE      | `/marcas/{id}`   | Excluir marca      |
| GET         | `/modelos`       | Consultar modelos  |
| POST        | `/modelos`       | Cadastrar modelo   |
| PUT         | `/modelos/{id}`  | Atualizar modelo   |
| DELETE      | `/modelos/{id}`  | Excluir modelo     |
| GET         | `/veiculos`      | Consultar veículos |
| POST        | `/veiculos`      | Cadastrar veículo  |
| PUT         | `/veiculos/{id}` | Atualizar veículo  |
| DELETE      | `/veiculos/{id}` | Excluir veículo    |

Os caminhos devem ser conferidos com os métodos efetivamente definidos nos controllers antes da entrega final.

### Filtros de veículos

O endpoint de consulta de veículos permite aplicar filtros por:

* Marca;
* Modelo;
* Status;
* Ano;
* Preço mínimo;
* Preço máximo.

Os filtros podem ser utilizados individualmente ou combinados, conforme os parâmetros implementados no controller.

Exemplos de consultas realizadas no Postman:

```http
GET http://localhost:8080/veiculos
GET http://localhost:8080/veiculos?marca=Honda
GET http://localhost:8080/veiculos?modelo=Civic
GET http://localhost:8080/veiculos?status=DISPONIVEL
GET http://localhost:8080/veiculos?ano=2022
```

Os nomes e formatos dos parâmetros devem corresponder aos definidos no código da aplicação.

## 🧪 Testes e validações

Durante a verificação funcional do projeto, foram realizadas operações de consulta, cadastro, atualização e exclusão pelo Postman, além de consultas ao banco de dados pelo MySQL Workbench.

Entre as verificações realizadas estão:

* Consulta de veículos cadastrados;
* Cadastro de veículo e confirmação de sua persistência;
* Atualização de informações;
* Exclusão de veículo e verificação da resposta posterior;
* Aplicação de filtros de consulta;
* Envio de dados inválidos para verificar as validações;
* Consulta dos relacionamentos entre marca, modelo e veículo no banco de dados.

Também foi verificada uma resposta HTTP `400 Bad Request` para uma requisição inválida e uma resposta `404 Not Found` após a exclusão de um veículo consultado posteriormente pelo mesmo identificador.

Os testes manuais do Postman e os testes automatizados do Maven são verificações diferentes e devem ser documentados separadamente.

## 🔐 Boas práticas de segurança

* Não publicar senhas reais ou credenciais de acesso ao banco.
* Utilizar variáveis de ambiente para informações sensíveis.
* Manter arquivos locais de configuração fora do versionamento, conforme o `.gitignore`.
* Utilizar dados de teste durante o desenvolvimento.
* Revisar as configurações de CORS antes de disponibilizar a aplicação em um ambiente de produção.

## 🎯 Objetivo acadêmico

O projeto tem como objetivo aplicar conceitos de programação orientada a objetos, desenvolvimento de APIs REST, integração com banco de dados relacional, validação de dados e versionamento de código.

A implementação também permite praticar operações CRUD, consultas com filtros e organização de responsabilidades entre controllers, entidades e repositórios.

## 👨‍💻 Autor

**Kennyson Pacheco Sousa**

Projeto acadêmico — Sistema Automotivo: Gestão de Estoque de Veículos.

## 📂 Repositório

[GitHub — gestao-veiculos-](https://github.com/kennysonpachecosousa-lgtm/gestao-veiculos-)
