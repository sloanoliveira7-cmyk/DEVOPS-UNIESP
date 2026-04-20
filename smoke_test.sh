#!/bin/bash
APP_URL="http://localhost:8080/health"
echo "Executando Smoke Test na URL: $APP_URL"
for i in {1..5}; do
  STATUS_CODE=$(curl -s -o /dev/null -w "%{http_code}" $APP_URL)
  if [ "$STATUS_CODE" -eq 200 ]; then
    echo "Smoke Test bem-sucedido! HTTP 200."
    exit 0
  fi
  echo "Tentativa $i: Aplicação ainda não respondeu (Status: $STATUS_CODE). Aguardando..."
  sleep 2
done
echo "Smoke Test falhou após 5 tentativas!"
exit 1
