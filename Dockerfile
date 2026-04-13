# Estágio de Build
FROM golang:1.21-alpine AS builder
WORKDIR /app
COPY . .
RUN go mod init app && go mod tidy
RUN go build -o main .

# Estágio Final (Imagem leve para produção)
FROM alpine:latest
WORKDIR /root/
COPY --from=builder /app/main .
EXPOSE 8080
CMD ["./main"]
