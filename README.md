<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h1 align="center">PizzaYOLO</h1>

  <p align="center">
    Repositório de Backend para o projeto em Java Spring Boot realizado como teste para processo seletivo na empresa CI&T.
    <br />
  </p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Sumário</summary>
  <ol>
    <li>
      <a href="#sobre-o-projeto">Sobre o Projeto</a>
    </li>
    <li>
      <a href="#tecnologias-utilizadas">Tecnologias utilizadas</a></li>
    </li>
    <li>
      <a href="#rodando-o-projeto">Rodando o projeto</a>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Sobre o Projeto

PizzaYOLO foi um projeto desenvolvido em novembro de 2021, para o processo seletivo para a vaga de Desenvolvedor Java Junior na empresa CI&T.

# PizzaYOLO

## Objetivo
O projeto visa construir um backend de uma pizzaria, permitindo ver uma lista paginada de pizzas disponíveis, adicionar, editar e remover pizzas.

## Requisitos

- Aceitar o cadastro de novos sabores de pizza, além de listar e deletar os
registros.
- A entidade deve ter os atributos:
  - id
  - nome
  - descrição
  - preço
- Fazer validações para diferentes cenários
- Utilizar as melhores práticas REST! (http verbs, http response codes,
etc...)

### Diferencias (Uma das opções abaixo é obrigatório):
- Documentar sua API com o Swagger.
- Subir seu banco de dados utilizando Docker.
- Escrever testes unitários (jUnit).
- Paginação no método de listagem

# Tecnologias utilizadas

Esse projeto foi desenvolvido com:

* Linguagem base: [Java 11](https://www.java.com/en/)
* Framework backend: [Spring Boot](https://spring.io/projects/spring-boot)
* Testes Unitários: [JUnit5](https://junit.org/junit5/) e [Mockito](https://site.mockito.org/)
* Banco de dados: [PostgreSQL](https://www.postgresql.org/)
* Documentação: [Swagger](https://swagger.io/)
<!-- * Deploy: [Heroku](https://www.heroku.com/) e [Docker](https://www.docker.com) -->


<!-- GETTING STARTED -->
# Rodando o projeto

Para rodar o projeto em sua máquina, você deverá clonar o repositório e executar o aqruivo jar:

1. Clonar o projeto
   ```sh
   git clone git@github.com:ricardorosa-dev/pizzayolo.git
   ```
2. Entre na pasta e instale as dependências
   ```sh
   cd pizzayolo
   ```
3. Empacote a aplicação:
   ```sh
   mvn clean
   mvn package
   ```
4. Entre na pasta target:
   ```sh
   cd target
   ```
5. Rode a aplicação:
   ```sh
   java -jar PizzaYOLO-0.0.1-SNAPSHOT.jar
   ```
<!-- ### Instalando pelo Docker
1. Baixe a imagem
```sh
  docker pull ricardorosadev/central-de-erros
```
2. Rode a imagem pelo Docker
```sh
  docker run -d -p 8080:8080 ricardorosadev/central-de-erros:latest
``` -->

## Endpoints

- **/pizza**: Acessa os usuários do sistema de central de erros

O projeto foi documentado através do Swagger.
Todas as rotas, métodos e modelos das entidades podem ser vistos com detalhes acessando, dentro do projeto, a url na url /swagger-ui.html).

<!-- CONTACT -->
## Contato
ricardorosa@gmail.com
https://www.linkedin.com/in/ricardorosa-dev/


