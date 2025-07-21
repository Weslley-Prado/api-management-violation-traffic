
# Documentação da API de Geração de Token

## Visão Geral

Este documento descreve a API de Geração de Token, uma solução robusta e escalável para geração de tokens de autenticação. A API foi desenvolvida utilizando **Java 17** e **Spring Boot**, seguindo o padrão de **Arquitetura Hexagonal** (Portas e Adaptadores) para garantir modularidade, testabilidade e manutenibilidade. O projeto é conteinerizado e pode ser facilmente implantado usando **Docker Compose**.

## Arquitetura

A API é estruturada com base na **Arquitetura Hexagonal**, que separa a lógica de negócio das preocupações de infraestrutura. Essa abordagem promove baixo acoplamento e facilita a substituição ou modificação de componentes sem impactar a lógica central. A estrutura de pastas do projeto é organizada da seguinte forma:

```
src/main/java/com/example/tokenmanager
├── adapter
│   ├── in
│   │   ├── bound
│   │   ├── controller
│   │   └── mapper
│   └── out
│       ├── bound
│       ├── mapper
│       └── processor
├── application
│   ├── domain
│   ├── port
│   └── service
├── config
├── repository
└── token

```

### Descrição dos Pacotes

-   **adapter**: Gerencia interações e integrações externas.
    -   **in**: Adaptadores de entrada para receber requisições externas.
        -   `bound`: Define os limites para operações de entrada.
        -   `controller`: Controladores REST para lidar com requisições HTTP.
        -   `mapper`: Mapeia DTOs de entrada para modelos de domínio.
    -   **out**: Adaptadores de saída para sistemas externos.
        -   `bound`: Define os limites para operações de saída.
        -   `mapper`: Mapeia modelos de domínio para DTOs de saída.
        -   `processor`: Processa dados de saída.
-   **application**: Contém a lógica de negócio e orquestração.
    -   `domain`: Modelos de domínio e regras de negócio.
    -   `port`: Interfaces que definem o contrato para os adaptadores.
    -   `service`: Implementa a lógica de negócio e coordena interações.
-   **config**: Arquivos de configuração e setup da aplicação.
-   **repository**: Gerencia a persistência e recuperação de dados.
-   **token**: Lógica central para geração e gerenciamento de tokens.

Essa estrutura garante que a lógica de negócio (no pacote `application`) permaneça independente de sistemas externos, tornando a API adaptável a mudanças em persistência ou integrações externas.

## Implantação

A API é conteinerizada usando Docker e pode ser implantada com **Docker Compose**. Para iniciar a aplicação, siga estes passos:

1.  Certifique-se de que **Docker** e **Docker Compose** estão instalados no seu sistema.

2.  Clone o repositório contendo o projeto.

3.  Navegue até o diretório raiz do projeto onde está o arquivo `docker-compose.yml`.

4.  Execute o seguinte comando para iniciar a API:

    ```bash
    docker-compose up -d
    
    ```


Esse comando construirá e iniciará os contêineres necessários, disponibilizando a API em `http://localhost:8080`.

## Endpoints da API

### Autenticar e Gerar Token

Este endpoint autentica um usuário e gera um token após uma autenticação bem-sucedida.

-   **Método**: `POST`
-   **URL**: `/auth/login`
-   **Content-Type**: `application/json`

#### Requisição

```json
{
  "username": "admin",
  "password": "admin123"
}

```

#### Exemplo de Requisição

```bash
curl --request POST \
  --url http://localhost:8080/auth/login \
  --header 'Content-Type: application/json' \
  --data '{
    "username": "admin",
    "password": "admin123"
  }'

```

#### Resposta

-   **Código de Status**: `200 OK`

-   **Corpo**:

    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    
    ```

-   **Respostas de Erro**:

    -   **400 Bad Request**: Corpo da requisição inválido ou campos obrigatórios ausentes.

        ```json
        {
          "error": "Nome de usuário ou senha inválidos"
        }
        
        ```

    -   **401 Unauthorized**: Falha na autenticação devido a credenciais incorretas.

        ```json
        {
          "error": "Não autorizado: Credenciais inválidas"
        }
        
        ```


## Tecnologias Utilizadas

-   **Java 17**: Linguagem de programação para o desenvolvimento do backend.
-   **Spring Boot**: Framework para construção da API, fornecendo injeção de dependências, suporte a REST e gerenciamento de configuração.
-   **Docker**: Conteinerização para implantação consistente.
-   **Docker Compose**: Orquestra a API e suas dependências.

## Como Começar

1.  Certifique-se de que os pré-requisitos (Docker, Docker Compose) estão instalados.
2.  Execute `docker-compose up -d` para iniciar a aplicação.
3.  Use o comando `curl` fornecido para testar o endpoint `/auth/login`.
4.  A API estará disponível em `http://localhost:8080`.

## Observações

-   A API utiliza JWT (JSON Web Tokens) para geração segura de tokens.
-   Certifique-se de que o pacote `application` contém a lógica de negócio principal, isolada de sistemas externos por meio de portas e adaptadores.
-   Para implantação em produção, configure variáveis de ambiente no arquivo `docker-compose.yml` para dados sensíveis, como chaves secretas.

Essa API oferece uma solução segura, escalável e manutenível para autenticação baseada em tokens, aproveitando os benefícios da Arquitetura Hexagonal e tecnologias modernas de Java.

## Contato

-   **Weslley Prado**: [weslleyrprado@gmail.com]
- **GitHub**: [Weslley-Prado](https://github.com/Weslley-Prado)