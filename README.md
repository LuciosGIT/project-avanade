# Decola Tech 2025 - RESTful API com deploy no Railway

API construída em **Java 17** com **Spring Boot 3** durante o **Decola Tech 2025**.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **OpenAPI (Swagger)**
- **Railway**

## 📐 Arquitetura

O projeto segue uma arquitetura baseada em camadas:

- **Controller**: Exposição dos endpoints REST.
- **Service**: Regras de negócio e processamento.
- **Repository**: Acesso ao banco de dados utilizando Spring Data JPA.

## 📊 Diagrama de Classes

O domínio da API foi abstraído utilizando **Figma**, facilitando a análise e implementação da solução.

classDiagram
  class User {
    -String name
    -Account account
    -Feature[] features
    -Card card
    -News[] news
  }

  class Account {
    -String number
    -String agency
    -Number balance
    -Number limit
  }

  class Feature {
    -String icon
    -String description
  }

  class Card {
    -String number
    -Number limit
  }

  class News {
    -String icon
    -String description
  }

  User "1" *-- "1" Account
  User "1" *-- "N" Feature
  User "1" *-- "1" Card
  User "1" *-- "N" News


## 📘 Documentação da API

A documentação interativa foi gerada com **Swagger/OpenAPI** e pode ser acessada através do link:

🔗 [Swagger UI - Documentação](project-avanade-production.up.railway.app/swagger-ui.html)

## 📌 Endpoints Principais

### Consultar Usuário por ID

```
GET /users/{id}
```
Exemplo:

```bash
GET https://sdw-2023-prd.up.railway.app/users/1
```

## 🛠️ Executando o Projeto Localmente

1. **Clone o repositório**

```bash
git clone https://github.com/seu-usuario/santander-dev-week-2023-api.git
cd santander-dev-week-2023-api
```

2. **Configure o ambiente**

Certifique-se de ter o **Java 17** e o **Maven** instalados.

3. **Execute a aplicação**

```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📂 Estrutura do Projeto

```
├── src
│   ├── main
│   │    ├── java
│   │    │    └── com.example.avanade_project
│   │    └── resources
│   │         └── application.properties
│   └── test
├── pom.xml
└── README.md
```


## 📄 Licença

Este projeto está sob a licença **MIT**. Sinta-se livre para utilizá-lo, modificá-lo e compartilhar!

---


