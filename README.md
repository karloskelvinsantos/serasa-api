# Serasa Api

Api desenvolvida referente ao desafio da Serasa Experian

## Requirements

For building and running the application you need:

- JDK 11
- Maven 3

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `br.com.serasa.SerasaAppApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
./mvnw spring-boot:run
```

## Auth

Para autenticar, utilizar o endpoint {context}/auth 

Usuário já existente na aplicação: username: admin - password: admin

Rotas mapeadas com POST, utilizar o token retornado na autenticação, na aba Authorization, type Baerer Token, utilizando o Postman

## Executing Tests

For execute the tests, run command below:

```shell
./mvnw test
```

## Test Api using Open API:

http://localhost:8080/swagger-ui.html

## Test Api using Postman

Na raiz do projeto, contém uma collection com os endpoints mapeados no Postman.

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
