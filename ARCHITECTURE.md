# Arquitetura Hexagonal (Ports & Adapters)

## Visão Geral

Este projeto implementa a **Arquitetura Hexagonal** (também conhecida como Ports & Adapters), um padrão arquitetural que promove a separação de responsabilidades e facilita a testabilidade.

## Estrutura de Diretórios

```
src/main/java/com/uniesp/desafio/
├── domain/                    # Camada de Domínio (Núcleo)
│   ├── User.java             # Entidade de domínio
│   ├── Product.java          # Entidade de domínio
│   └── Calculator.java       # Serviço de domínio
├── application/              # Camada de Aplicação
│   ├── ports/                # Portas (Interfaces)
│   │   ├── UserRepository.java
│   │   └── ProductRepository.java
│   └── usecases/             # Casos de Uso (Serviços)
│       ├── UserService.java
│       └── ProductService.java
├── infrastructure/           # Camada de Infraestrutura
│   └── persistence/          # Adaptadores de Persistência
│       ├── UserEntity.java
│       ├── ProductEntity.java
│       ├── UserJpaRepository.java
│       ├── ProductJpaRepository.java
│       ├── UserRepositoryAdapter.java
│       └── ProductRepositoryAdapter.java
└── presentation/             # Camada de Apresentação
    ├── HealthController.java
    ├── UserController.java
    └── ProductController.java
```

## Camadas

### 1. **Domain (Núcleo)**
- Contém a lógica de negócio pura
- Independente de frameworks e tecnologias
- Classes: `User`, `Product`, `Calculator`
- Sem dependências externas

### 2. **Application**
- Orquestra a lógica de negócio
- Define as **Portas** (interfaces)
- Implementa os **Casos de Uso** (serviços)
- Exemplos: `UserService`, `ProductService`

### 3. **Infrastructure**
- Implementa as **Portas** através de **Adaptadores**
- Contém detalhes técnicos (banco de dados, frameworks)
- Exemplos: `UserRepositoryAdapter`, `ProductRepositoryAdapter`

### 4. **Presentation**
- Adaptadores para entrada/saída HTTP
- Controllers REST
- Exemplos: `UserController`, `HealthController`

## Princípios SOLID Aplicados

### **S** - Single Responsibility Principle
- Cada classe tem uma única responsabilidade
- `UserService` apenas gerencia usuários
- `ProductService` apenas gerencia produtos

### **O** - Open/Closed Principle
- Aberto para extensão, fechado para modificação
- Novas implementações de repositório sem alterar o código existente

### **L** - Liskov Substitution Principle
- Adaptadores podem ser substituídos sem quebrar o código
- `UserRepositoryAdapter` implementa `UserRepository`

### **I** - Interface Segregation Principle
- Interfaces específicas e focadas
- `UserRepository` apenas define operações de usuário

### **D** - Dependency Inversion Principle
- Dependência em abstrações, não em implementações concretas
- `UserService` depende de `UserRepository` (interface), não de `UserRepositoryAdapter`

## Fluxo de Requisição

```
HTTP Request
    ↓
[Presentation Layer - Controller]
    ↓
[Application Layer - Service]
    ↓
[Domain Layer - Business Logic]
    ↓
[Application Layer - Port (Interface)]
    ↓
[Infrastructure Layer - Adapter]
    ↓
[Database]
```

## Benefícios

1. **Testabilidade**: Fácil criar mocks das portas
2. **Manutenibilidade**: Lógica de negócio isolada
3. **Flexibilidade**: Trocar implementações facilmente
4. **Escalabilidade**: Adicionar novas funcionalidades sem impacto
5. **Independência**: Lógica de negócio não depende de frameworks

## Exemplo de Teste

```java
@Mock
private UserRepository userRepository;

@Test
public void testCreateUserSuccess() {
    User user = new User(null, "João Silva");
    User savedUser = new User(1L, "João Silva");
    
    when(userRepository.save(user)).thenReturn(savedUser);
    
    User result = userService.createUser(user);
    
    assertEquals(1L, result.getId());
}
```

A porta `UserRepository` é mockada, permitindo testar `UserService` sem dependência de banco de dados.

## Conclusão

A Arquitetura Hexagonal fornece uma base sólida para desenvolvimento de aplicações escaláveis, mantíveis e testáveis, alinhada com os princípios SOLID e as melhores práticas de engenharia de software.
