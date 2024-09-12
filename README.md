
# Projeto de testes de API SoftDesign

## Descrição

Projeto de automação de testes de API - Design pattern MVC (model view controller)

## Tecnologias

- Java
- Maven
- JUnit
- RestAssured

## Estrutura do projeto

```
src
│main
| ├─java
| | ├─org.example
| | | ├─config
| | | | ├─ConfigReader.java
| | | ├─model
| | | | ├─login
| | | | | ├─LoginResquest.java
| | | | ├─product
| | | | | ├─ProductResquest.java
| | | | | ├─ProductResponse.java
| | | | ├─user
| | | | | ├─UserResponse.java
| ├─resources
| | ├─config.properties
│test
│  ├─java
│  │  ├─org.example.controller
│  │  │  │  ├─AuthControllerTest.java
│  │  │  │  |─ProductControllerTest.java
│  │  │  │  |─UserControllerTest.java
pom.xml
README.md
```

## Execução

Para executar o projeto, basta executar o comando abaixo:

```
mvn test
```

## Relatório

O relatório de execução dos testes é gerado na pasta `target/surefire-reports`
-  Informações com resultados de sucesso e erro: .TXT
- Informações mais detalhadas de execução com trechos de código: .XML


## Autor

- Ricardo Cremonez