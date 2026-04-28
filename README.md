# 🚀 Desafio DevOps: O Pipeline Inquebrável (Edição Java + SOLID)

## 📌 Contexto

Este projeto foi migrado de **Go** para **Java (Spring Boot)** seguindo a **Arquitetura Hexagonal** e os princípios **SOLID**. A aplicação implementa um pipeline de CI/CD completo com testes automatizados, migrations de banco de dados e smoke tests.

## 🎯 O Grande Desafio

O objetivo foi realizar a migração e implementação completa do pipeline de CI/CD, seguindo as regras abaixo:

### 1. Reescrita para Java ✅

* **Linguagem:** O projeto foi totalmente portado para **Java 17** com **Spring Boot 3.2.0**.
* **Arquitetura:** Implementada a **Arquitetura Hexagonal (Ports & Adapters)**.
* **Princípios:** Aplicação rigorosa de **SOLID**.

### 2. Desenvolvimento com TDD ✅

* **Testes Primeiro:** Bugs corrigidos e funcionalidades implementadas utilizando **TDD (Test Driven Development)**.
* **Cobertura:** O pipeline valida que todos os testes passam antes de avançar.
* **Testes Inclusos:**
  - `CalculatorTest`: Testa a função `sum()` (bug corrigido) e `classify()`
  - `ProductTest`: Testa cálculo de preços com descontos
  - `UserServiceTest`: Testa criação e validação de usuários

### 3. Pipeline de CI/CD (GitHub Actions) ✅

Implementado com três etapas bem definidas:

#### **Etapa 1: Commit (Build & Testes)**
- Setup do Java 17
- Execução de testes unitários
- Build da aplicação Maven
- Build e push da imagem Docker (com tag SHA para imutabilidade)

#### **Etapa 2: Homologação (Staging)**
- Puxar imagem imutável do registry
- Executar migrations com Flyway automaticamente
- Validar schema do banco de dados

#### **Etapa 3: Produção**
- Deploy da imagem em container
- Executar migrations
- **Smoke Test**: Validar endpoint `/health` retorna HTTP 200
- Testes adicionais dos endpoints

## 📋 Critérios de Avaliação

| Item | Descrição | Peso | Status |
| :--- | :--- | :--- | :--- |
| **Java & Hexagonal** | Migração para Java com Arquitetura Hexagonal | 20% | ✅ |
| **SOLID & TDD** | Princípios SOLID aplicados e TDD guiando desenvolvimento | 20% | ✅ |
| **Imutabilidade** | Imagem Docker construída uma vez com tag SHA | 15% | ✅ |
| **Automação de DB** | Migrations via Flyway no pipeline | 15% | ✅ |
| **Pipeline & Resiliência** | Pipeline com dependências corretas | 20% | ✅ |
| **Smoke Test** | Validação real do `/health` em produção | 10% | ✅ |

## 🛠️ Estrutura do Projeto

```
src/main/java/com/uniesp/desafio/
├── domain/                    # Lógica de negócio pura
│   ├── User.java
│   ├── Product.java
│   └── Calculator.java
├── application/              # Casos de uso e portas
│   ├── ports/
│   │   ├── UserRepository.java
│   │   └── ProductRepository.java
│   └── usecases/
│       ├── UserService.java
│       └── ProductService.java
├── infrastructure/           # Adaptadores
│   └── persistence/
│       ├── UserEntity.java
│       ├── ProductEntity.java
│       └── *Adapter.java
└── presentation/             # Controllers REST
    ├── HealthController.java
    ├── UserController.java
    └── ProductController.java
```

Para mais detalhes, consulte [ARCHITECTURE.md](ARCHITECTURE.md).

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.9+
- Docker
- PostgreSQL 15 (ou usar Docker)

### Executar Localmente

```bash
# 1. Clonar o repositório
git clone https://github.com/sloanoliveira7-cmyk/DEVOPS-UNIESP.git
cd DEVOPS-UNIESP

# 2. Iniciar PostgreSQL com Docker
docker run -d \
  --name postgres-devops \
  -e POSTGRES_DB=devops \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=password \
  -p 5432:5432 \
  postgres:15-alpine

# 3. Executar testes
mvn clean test

# 4. Build da aplicação
mvn clean package

# 5. Executar a aplicação
java -jar target/desafio-devops-1.0.0.jar
```

A aplicação estará disponível em `http://localhost:8080`

### Endpoints Disponíveis

- **Health Check**: `GET /health` → Retorna "Sistema Operante"
- **Users**:
  - `POST /users` → Criar usuário
  - `GET /users` → Listar usuários
  - `GET /users/{id}` → Buscar usuário
  - `DELETE /users/{id}` → Deletar usuário
- **Products**:
  - `POST /products` → Criar produto
  - `GET /products` → Listar produtos com preços calculados
  - `GET /products/{id}` → Buscar produto
  - `DELETE /products/{id}` → Deletar produto

### Exemplo de Requisição

```bash
# Criar usuário
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "João Silva"}'

# Criar produto
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Notebook", "basePrice": 1000.00}'

# Verificar saúde
curl http://localhost:8080/health
```

## 🐳 Docker

### Build da Imagem

```bash
docker build -t desafio-devops:latest .
```

### Executar com Docker Compose

```bash
docker-compose up -d
```

## 📊 Testes

```bash
# Executar todos os testes
mvn clean test

# Executar teste específico
mvn test -Dtest=CalculatorTest

# Com cobertura
mvn clean test jacoco:report
```

## 🔄 Pipeline de CI/CD

O pipeline é acionado automaticamente em:
- Push para `main`
- Pull Requests para `main`

Visualize em: `.github/workflows/pipeline.yml`

### Etapas do Pipeline

1. **Commit Stage**: Build, testes e push de imagem
2. **Staging Stage**: Migrations e validação
3. **Production Stage**: Deploy e smoke test

## 🐛 Bugs Corrigidos

| Bug Original | Correção |
| :--- | :--- |
| Função `Soma()` retornava `a + b` (correto, mas sem testes) | Implementada com testes completos em `CalculatorTest` |
| Migration SQL com sintaxe quebrada | Corrigida e migrada para Flyway em `V1__Initial.sql` |
| Pipeline sem dependências entre jobs | Adicionadas dependências corretas com `needs:` |
| Sem automação de migrations | Implementado Flyway no pipeline |

## 📚 Documentação Adicional

- [ARCHITECTURE.md](ARCHITECTURE.md) - Detalhes da Arquitetura Hexagonal
- [pom.xml](pom.xml) - Dependências do projeto
- [.github/workflows/pipeline.yml](.github/workflows/pipeline.yml) - Pipeline de CI/CD

## ✅ Checklist de Conclusão

- ✅ Migração para Java Spring Boot
- ✅ Arquitetura Hexagonal implementada
- ✅ Princípios SOLID aplicados
- ✅ TDD com testes unitários
- ✅ Pipeline de CI/CD com 3 etapas
- ✅ Migrations com Flyway
- ✅ Smoke tests
- ✅ Docker multi-stage build
- ✅ Documentação completa

## 👥 Integrantes

- Sloan Oliveira (sloanoliveira7-cmyk)

## 📝 Licença

Este projeto é fornecido como parte do desafio educacional.

---

**Nota**: O professor pode fazer commits propositais para testar a resiliência do pipeline. O projeto está pronto para detectar e corrigir tais intervenções.
