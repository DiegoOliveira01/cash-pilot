# 💰 Cash Pilot — Backend

> API REST para gerenciamento de finanças pessoais, construída com Java 21 e Spring Boot 3.

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-green?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Railway](https://img.shields.io/badge/Deploy-Railway-purple?style=flat-square&logo=railway)](https://cashpilot-backend-production.up.railway.app)

---

## 🔗 Links

| | |
|---|---|
| 🌐 **Frontend (Vercel)** | [cashpilot-frontend.vercel.app](https://cashpilot-frontend.vercel.app) |
| 📁 **Repositório Frontend** | [github.com/DiegoOliveira01/cashpilot-frontend](https://github.com/DiegoOliveira01/cashpilot-frontend) |
| 📄 **Swagger / Documentação** | [cashpilot-backend-production.up.railway.app/swagger-ui/index.html](https://cashpilot-backend-production.up.railway.app/swagger-ui/index.html) |
| 💼 **LinkedIn** | [linkedin.com/in/diego-oliveira-da-fonte](https://www.linkedin.com/in/diego-oliveira-da-fonte-2395b0223/) |
| 🗂️ **Portfólio** | [diegooliveira01.github.io](https://diegooliveira01.github.io) |

---

## 📸 Interface do Sistema

> O frontend consome esta API e está disponível em [cashpilot-frontend.vercel.app](https://cashpilot-frontend.vercel.app)


| Login | Dashboard |
|---|---|
| ![Login](https://github.com/DiegoOliveira01/cashpilot-backend/blob/503dbaf598858e330d6433700cc90159cd8b5476/Readme_img_login.png) | ![Dashboard](https://github.com/DiegoOliveira01/cashpilot-backend/blob/503dbaf598858e330d6433700cc90159cd8b5476/Readme_img_dashboard.png) |

---

## 🚀 Funcionalidades

- ✅ Cadastro e autenticação de usuários com **JWT**
- ✅ CRUD completo de transações financeiras
- ✅ Classificação por tipo: **Receita (INCOME)** e **Despesa (EXPENSE)**
- ✅ Resumo financeiro: total de receitas, despesas e saldo
- ✅ Senhas criptografadas com **BCrypt**
- ✅ Documentação interativa com **Swagger UI**
- ✅ Proteção de endpoints com **Spring Security**

---

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| Java 21 | Linguagem principal |
| Spring Boot 3.4 | Framework base |
| Spring Security | Autenticação e autorização |
| JWT (jjwt 0.12.6) | Tokens de autenticação |
| Spring Data JPA | Acesso ao banco de dados |
| PostgreSQL | Banco de dados relacional |
| Hibernate | ORM |
| Lombok | Redução de boilerplate |
| Springdoc OpenAPI | Documentação Swagger |
| Railway | Deploy em nuvem |

---

## 🔐 Fluxo de Autenticação JWT

```
┌─────────────┐         ┌─────────────┐         ┌─────────────┐
│   Frontend  │         │   Backend   │         │   Database  │
└──────┬──────┘         └──────┬──────┘         └──────┬──────┘
       │                       │                       │
       │  POST /auth/login     │                       │
       │  { email, password }  │                       │
       │──────────────────────>│                       │
       │                       │  SELECT user          │
       │                       │  WHERE email = ?      │
       │                       │──────────────────────>│
       │                       │<──────────────────────│
       │                       │  BCrypt.verify()      │
       │                       │  generateToken()      │
       │  { token: "eyJ..." }  │                       │
       │<──────────────────────│                       │
       │                       │                       │
       │  GET /transactions    │                       │
       │  Authorization: Bearer│                       │
       │──────────────────────>│                       │
       │                       │  JwtFilter validates  │
       │                       │  token on every req   │
       │  [ transactions ]     │                       │
       │<──────────────────────│                       │
```

---

## 📁 Estrutura do Projeto

```
src/main/java/com/diego/cashpilot/
│
├── controller/          # Endpoints REST
│   ├── AuthController   # POST /auth/register, /auth/login
│   ├── TransactionController
│   └── UserController
│
├── service/             # Regras de negócio
│   ├── UserService
│   └── TransactionService
│
├── repository/          # Acesso ao banco (JPA)
│   ├── UserRepository
│   └── TransactionRepository
│
├── model/               # Entidades JPA
│   ├── User
│   ├── Transaction
│   └── enums/
│       ├── Role
│       ├── TransactionType
│       └── CategoryType
│
├── dto/                 # Objetos de transferência
│   ├── UserRequestDTO
│   ├── UserResponseDTO
│   ├── LoginRequestDTO
│   ├── AuthResponseDTO
│   ├── TransactionRequestDTO
│   ├── TransactionResponseDTO
│   └── TransactionSummaryDTO
│
├── security/            # JWT e filtros
│   ├── JwtService
│   ├── JwtFilter
│   └── CustomUserDetailsService
│
└── config/              # Configurações
    ├── SecurityConfig
    └── CorsConfig
```

---

## 📡 Endpoints da API

### Autenticação — público

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/auth/register` | Cadastrar novo usuário |
| `POST` | `/auth/login` | Autenticar e receber token JWT |

### Transações — requer token JWT

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/transactions` | Listar todas as transações do usuário |
| `POST` | `/transactions` | Criar nova transação |
| `PUT` | `/transactions/{id}` | Atualizar transação |
| `DELETE` | `/transactions/{id}` | Deletar transação |
| `GET` | `/transactions/summary` | Resumo financeiro (receitas, despesas, saldo) |

### Exemplos de requisição

**Registro:**
```json
POST /auth/register
{
  "name": "Diego Oliveira",
  "email": "diego@email.com",
  "password": "123456"
}
```

**Login:**
```json
POST /auth/login
{
  "email": "diego@email.com",
  "password": "123456"
}
```

**Criar transação** (com Bearer token no header):
```json
POST /transactions
{
  "description": "Salário",
  "amount": 3000.00,
  "type": "INCOME",
  "date": "2026-04-01"
}
```

---

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 21+
- Maven
- PostgreSQL

### Passo a passo

**1. Clone o repositório**
```bash
git clone https://github.com/DiegoOliveira01/cash-pilot.git
cd cash-pilot
```

**2. Configure o banco de dados**

Crie um banco PostgreSQL:
```sql
CREATE DATABASE cashpilot_db;
```

**3. Configure as variáveis de ambiente**

Copie o arquivo de exemplo:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edite o `application.properties` com suas credenciais:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cashpilot_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false

jwt.secret=sua-chave-secreta-muito-longa-aqui
jwt.expiration=86400000
```

**4. Rode a aplicação**
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

**5. Acesse o Swagger**
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🌐 Deploy

A API está hospedada no **Railway** com banco de dados PostgreSQL dedicado.

**URL base:** `https://cashpilot-backend-production.up.railway.app`

**Swagger em produção:** [cashpilot-backend-production.up.railway.app/swagger-ui/index.html](https://cashpilot-backend-production.up.railway.app/swagger-ui/index.html)

---

## 👨‍💻 Autor

**Diego Oliveira**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Diego_Oliveira-blue?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/diego-oliveira-da-fonte-2395b0223/)
[![Portfolio](https://img.shields.io/badge/Portfólio-diegooliveira01.github.io-gray?style=flat-square&logo=github)](https://diegooliveira01.github.io)

