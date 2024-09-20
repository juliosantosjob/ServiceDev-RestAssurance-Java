# Testes da API ServeRest

Este projeto contém testes automatizados para a API ServeRest usando Java e JUnit 5. Os testes cobrem cenários de registro de usuários, incluindo casos de sucesso e falha.

## Estrutura do Projeto

- **src/main**: Contém configurações de variaveis de ambiente utilizando o .properties
- **src/test**: Contém as classes de teste para registro de usuários e outras funcionalidades da API.

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/juliosantosjob/ServiceDev-RestAssurance-Junit.git
   cd ServiceDev-RestAssurance-Junit
   ```

2. Instale as dependências e execute os testes:
   ```bash
   mvn clean test
   ```

## Configuração

Todas as propriedades de configuração, como a URL base da API, podem ser encontradas e editadas no arquivo `config.properties` localizado em `src/main/resources`.
