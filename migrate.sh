#!/bin/bash

# Script para executar migrações do banco de dados usando Flyway
# Compatível com o pipeline de CI/CD

set -e

echo "🔄 Iniciando migrações de banco de dados..."

# Verificar variáveis de ambiente
if [ -z "$DATABASE_URL" ]; then
    echo "❌ Erro: DATABASE_URL não está definida"
    exit 1
fi

if [ -z "$DATABASE_USER" ]; then
    echo "❌ Erro: DATABASE_USER não está definida"
    exit 1
fi

if [ -z "$DATABASE_PASSWORD" ]; then
    echo "❌ Erro: DATABASE_PASSWORD não está definida"
    exit 1
fi

# Executar Flyway
echo "📦 Executando Flyway..."
docker run --rm \
    -v $(pwd)/src/main/resources/db/migration:/flyway/sql \
    -e FLYWAY_URL="$DATABASE_URL" \
    -e FLYWAY_USER="$DATABASE_USER" \
    -e FLYWAY_PASSWORD="$DATABASE_PASSWORD" \
    flyway/flyway:9.22.3 migrate

echo "✅ Migrações concluídas com sucesso!"
