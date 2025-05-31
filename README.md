# Lanches Caieiras

## Índice

- [Descrição](#descrição)
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Como usar](#como-usar)
- [Testando a API com Postman](#testando-a-api-com-postman)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contrato da API](#contrato-da-api)

## Descrição

Sistema para gerenciamento de pedidos de uma lanchonete, desenvolvido no Tech Challange - Fase 1 da pós-graduação FIAP 11SOAT.

Este projeto tem como objetivo simular o funcionamento de uma lanchonete, permitindo o cadastro cleinte, items de alimentação, pedidos de clientes, pagamentos e notificações aplicando os conceitos de arquitetura hexagonal.

## Requisitos

- Docker
- Docker Compose
- Postman (opcional, para testes de API)

## Instalação

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/11soat-lanchonete-caieiras.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd 11soat-lanchonete-caieiras/lanchesCaieiras
   ```
3. Garanta que tenha a permissão correta de execução ao Dockerfile:
   ```bash
   chmod 0755 lanchesCaieiras/DockerFile
   ```
4. Crie um arquivo `.env` na raiz do repositório com o seguinte modelo:
   ```env
   POSTGRES_DB=postgres
   POSTGRES_JDBC=jdbc:postgresql://db-postgres-lncr:5432/postgres
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD= # Definir senha do banco de dados Postgres
   LNCR_BASE_URL=http://localhost:8080 # URL base da API
   MERCADOPAGO_USER_ID= # Id do usuário da sua conta do Mercado Pago
   MERCADOPAGO_POS_ID= # Id do ponto de venda (POS) da sua conta do Mercado Pago
   MERCADOPAGO_CALLBACK_URL= # criar URL de callback do Mercado Pago Ex: https://webhook.site/
   MERCADOPAGO_QRCODE_URL=https://api.mercadopago.com/instore/orders/qr/seller/collectors/${MERCADOPAGO_USER_ID}/pos/${MERCADOPAGO_POS_ID}/qrs
   MERCADOPAGO_PAYMENTS_URL=https://api.mercadopago.com/v1/payments
   MERCADOPAGO_ACCESS_TOKEN= # Token de acesso da sua conta do Mercado Pago
   ```

## Como usar

Suba o ambiente com Docker Compose:
```bash
docker-compose up --build
```

## Estrutura do Projeto

- `src/` - Código-fonte principal
- `tests/` - Testes automatizados

## Contrato da API

O contrato da API está disponível no arquivo `lanches-caieiras-api-oas3.yaml` na raiz do projeto.

Para visualizar e interagir com a documentação da API, siga os passos:

1. Acesse o [Swagger Editor](https://editor.swagger.io/).
2. Clique em "File" > "Import File" e selecione o arquivo `lanches-caieiras-api-oas3.yaml` deste repositório.

Assim, você poderá visualizar e testar o contrato da API de forma interativa.

## Testes via Postman
Para testar a API, você pode usar o Postman. O arquivo de coleção do Postman está disponível no repositório.
1. Importe o arquivo `lanches-caieiras.postman_collection.json` no Postman.
2. Certifique-se de que o servidor esteja rodando.
3. A collection está organizada por Domínios:
    - Cliente
    - Items de Alimentação
    - Pedido do Cliente
    - Pagamento
    - Ordem de Preparo
    - Notificações
4. Execute as requisições conforme necessário.
