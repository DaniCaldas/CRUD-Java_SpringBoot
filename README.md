# Projeto de API REST de Lista de Tarefas

Bem-vindo ao projeto de API REST de Lista de Tarefas! Este projeto foi desenvolvido com o objetivo de gerenciar uma lista de tarefas utilizando tecnologias modernas como Java, Spring Boot, Lombok, Flyway, MySQL, Validation e Maven.

## Funcionalidades

- **Criar Tarefa:** Adicionar uma nova tarefa à lista.
- **Listar Tarefas:** Recuperar todas as tarefas.
- **Atualizar Tarefa:** Atualizar os detalhes de uma tarefa existente.
- **Excluir Tarefa:** Remover uma tarefa da lista.

## Tecnologias Utilizadas

- **Java:** Linguagem de programação utilizada para o desenvolvimento do projeto.
- **Spring Boot:** Framework que facilita a criação de aplicações Java com configuração mínima.
- **Lombok:** Biblioteca que reduz o código boilerplate, gerando automaticamente getters, setters, e outros métodos comuns.
- **Flyway:** Ferramenta de migração de banco de dados para versionamento e manutenção do esquema do banco de dados.
- **MySQL:** Sistema de gerenciamento de banco de dados relacional utilizado para armazenar as tarefas.
- **Validation:** Biblioteca de validação de dados utilizada para garantir a integridade dos dados de entrada.
- **Maven:** Ferramenta de gerenciamento de dependências e construção de projetos Java.

## Requisitos

- **Java 11 ou superior**
- **Maven**
- **MySQL**

## Link da API
    https://deplou-crud-springboot.onrender.com/lista

## Configuração do Projeto

1. **Clone o repositório:**

    ```sh
    git clone https://github.com/DaniCaldas/CRUD-Java_SpringBoot.git
    ```

2. **Navegue até o diretório do projeto:**

    ```sh
    cd CRUD-Java_SpringBoot
    ```
    
4. **Execute as migrações do Flyway:**

    O Flyway será executado automaticamente ao iniciar a aplicação, aplicando as migrações de banco de dados necessárias.

## Endpoints da API

A seguir estão os principais endpoints disponíveis na API:

- **GET /api/tarefas:** Retorna todas as tarefas.
- **POST /api/tarefas:** Cria uma nova tarefa.
- **PUT /api/tarefas/{id}:** Atualiza uma tarefa existente pelo ID.
- **DELETE /api/tarefas/{id}:** Remove uma tarefa pelo ID.

## Exemplos de Requisição

### Criar Tarefa

```sh
POST /api/tarefas
Content-Type: application/json

{
    "titulo": "Nova Tarefa",
    "descricao": "Descrição da nova tarefa",
    "status": "CONCLUIDO"
}
