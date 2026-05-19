# Multi-stage build para otimizar a imagem final
# Estágio 1: Build
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copiar arquivos de configuração
COPY pom.xml .

# Baixar dependências (para cache)
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Build da aplicação
RUN mvn clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar JAR do estágio de build
COPY --from=builder /app/target/desafio-devops-1.0.0.jar app.jar

# Expor porta
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/health || exit 1

# Executar aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
