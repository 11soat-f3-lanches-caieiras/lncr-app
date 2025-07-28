# Lanches Caieiras

## Índice

## Índice

- [Descrição](#descrição)
- [Requisitos](#requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Demonstração](#demonstração)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Arquitetura do Projeto](#arquitetura-do-projeto)
    - [1. Arquitetura de Infraestrutura em Kubernetes](#1-arquitetura-de-infraestrutura-kubernetes)
    - [2. Arquitetura da Aplicação - Clean Architecture](#2-arquitetura-da-aplicação---clean-architecture)
    - [3. Arquitetura Funcional](#3-arquitetura-da-funcional)
        - [3.1 Cadastro de Clientes](#31-cadastro-de-clientes)
        - [3.2 Cadastro de Items de Alimentação](#32---cadastro-de-items-de-alimentação)
        - [3.3 Novo Pedido - Checkout](#33---novo-pedido---checkout-)
        - [3.4 Pagamento](#34---pagamento)
        - [3.5 Pedido Recebido - Pagamento Confirmado](#35---pedido-recebido---pagamento-confirmado)
        - [3.6 Atualização Preparo](#36---atualização-preapro)
        - [3.7 Cancelamento Pedido](#37---cancelamento-pedido)
        - [3.8 Cancelamento Pagamento](#38---cancelamento-pagamento)
        - [3.9 Acompanhar Pedidos](#39---acompanhar-pedidos)
        - [3.10 Notificações](#310---notificações)
- [Contrato da API](#contrato-da-api)
- [Testes via Postman](#testes-via-postman)


## Descrição

Sistema para gerenciamento de pedidos de uma lanchonete, desenvolvido no Tech Challange - Fase 2 da pós-graduação FIAP 11SOAT.

Este projeto tem como objetivo simular o funcionamento de uma lanchonete, permitindo o cadastro clientes, items de alimentação, pedidos de clientes, pagamentos e notificações 
Foram aplicados os conceintos de Clean Archtecture e infraesturura em Kubernetes, utilizando Docker para containerização e Postgres como banco de dados.


## Requisitos

- Docker 
- Docker Desktop ou outro gerenciador de Cluster Kubernetes 
- Kubectl (para interagir com o cluster Kubernetes)
- Helm
- Postman (opcional, para testes de API)

## Instalação e Execução

1. Clone este repositório:
   ```bash
   git clone https://github.com/titoparizotto/11soat-f2-lanchonete-caieiras.git
   ```

2. Crie um arquivo `.env` na raiz do repositório com o seguinte modelo:
   ```env
   POSTGRES_URL=jdbc:postgresql://localhost:5432/postgres
   POSTGRES_DB=postgres
   POSTGRES_USER= {{ seu usuario }}
   POSTGRES_PASSWORD= {{sua senha }}
   MERCADOPAGO_CLIENT_ID= {{ seu client_id Mercado Pago }}
   MERCADOPAGO_SECRET_ID= {{ seu secret_id do Mercado Pago}}
   MERCADOPAGO_POS_ID= {{ seu pos_id do Mercado Pago }}
   ```

3. Execute o script para aprovisionar a infraestrutura kubernetes e iniciar aplicação e banco de dados
```bash
  run.sh
```

## Demonstração

Acesse o vídeo de demonstração:
[Fase 2 - Tech Challange - 11 SOAT](https://www.youtube.com/watch?v=LjR9S2n_6DQ)

# Estrutura do Projeto
## Arquitetura do Projeto
### 1. Arquitetura de Infraestrutura Kubernetes

```
┌─────────────────────────────────────────────────────────────────────────────────────┐
│                                CLUSTER KUBERNETES                                   │
├─────────────────────────────────────────────────────────────────────────────────────┤
│                                                                                     │
│  ┌──────────────────────┐                    ┌──────────────────────┐               │
│  │    LOAD BALANCER     │◄───────────────────┤   EXTERNAL TRAFFIC   │               │
│  │    (Service)         │                    │    (Port 8080)       │               │
│  │                      │                    └──────────────────────┘               │
│  └──────────┬───────────┘                                                           │
│             │                                                                       │
│             ▼                                                                       │
│  ┌──────────────────────────────────────────────────────────────────────────────┐   │
│  │                        LNCR-APP DEPLOYMENT                                   │   │
│  │  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐               │   │
│  │  │   APP POD 1     │  │   APP POD 2     │  │   APP POD N     │               │   │
│  │  │ ┌─────────────┐ │  │ ┌─────────────┐ │  │ ┌─────────────┐ │  ◄────────────┤   │
│  │  │ │Java Spring  │ │  │ │Java Spring  │ │  │ │Java Spring  │ │    HPA        │   │
│  │  │ │Boot App     │ │  │ │Boot App     │ │  │ │Boot App     │ │ (2-10 pods)   │   │
│  │  │ │Port: 8080   │ │  │ │Port: 8080   │ │  │ │Port: 8080   │ │               │   │
│  │  │ └─────────────┘ │  │ └─────────────┘ │  │ └─────────────┘ │               │   │
│  │  └─────────────────┘  └─────────────────┘  └─────────────────┘               │   │
│  └──────────────────────────────────────────────────────────────────────────────┘   │
│             │                                                                       │
│             │ (Internal Communication)                                              │
│             ▼                                                                       │
│  ┌──────────────────────────────────────────────────────────────────────────────┐   │
│  │                      LNCR-DB SERVICE (ClusterIP)                             │   │
│  │                            Port: 5432                                        │   │
│  └─────────────────────────────┬────────────────────────────────────────────────┘   │
│                                │                                                    │
│                                ▼                                                    │
│  ┌──────────────────────────────────────────────────────────────────────────────┐   │
│  │                        LNCR-DB DEPLOYMENT                                    │   │
│  │  ┌─────────────────────────────────────────────────────────────────────────┐ │   │
│  │  │                      POSTGRES POD                                       │ │   │
│  │  │ ┌─────────────────────────────────────────────────────────────────────┐ │ │   │
│  │  │ │                PostgreSQL 15.7                                      │ │ │   │
│  │  │ │                Port: 5432                                           │ │ │   │
│  │  │ │            (Single Replica - Recreate Strategy)                     │ │ │   │
│  │  │ └─────────────────────────────────────────────────────────────────────┘ │ │   │
│  │  └─────────────────────────────────────────────────────────────────────────┘ │   │
│  └──────────────────────────────────────────────────────────────────────────────┘   │
│                                │                                                    │
│                                ▼                                                    │
│  ┌──────────────────────────────────────────────────────────────────────────────┐   │
│  │                         PERSISTENT VOLUMES                                   │   │
│  │  ┌────────────────────┐              ┌────────────────────┐                  │   │
│  │  │   DB STORAGE       │              │  IMAGES STORAGE    │                  │   │
│  │  │  (Host Path)       │              │   (10Gi Volume)    │                  │   │
│  │  │ /var/lib/postgres  │              │   /app/images      │                  │   │
│  │  └────────────────────┘              └────────────────────┘                  │   │
│  └──────────────────────────────────────────────────────────────────────────────┘   │
│                                                                                     │
│  ┌──────────────────────────────────────────────────────────────────────────────┐   │
│  │                         CONFIGURATION                                        │   │
│  │  ┌────────────────────┐              ┌────────────────────┐                  │   │
│  │  │    CONFIGMAP       │              │      SECRETS       │                  │   │
│  │  │                    │              │                    │                  │   │
│  │  │ • Base URLs        │              │ • DB Credentials   │                  │   │
│  │  │ • Postgres Config  │              │ • MercadoPago Keys │                  │   │
│  │  │ • External APIs    │              │ • Sensitive Data   │                  │   │
│  │  └────────────────────┘              └────────────────────┘                  │   │
│  └──────────────────────────────────────────────────────────────────────────────┘   │
│                                                                                     │
└─────────────────────────────────────────────────────────────────────────────────────┘

Componentes principais:
• LoadBalancer Service: Expõe a aplicação externamente na porta 8080
• HPA (Horizontal Pod Autoscaler): Escala automaticamente de 2 a 10 pods baseado em CPU/Memória
• App Deployment: Pods da aplicação Java Spring Boot com health checks
• DB Service (ClusterIP): Comunicação interna com PostgreSQL
• DB Deployment: PostgreSQL com estratégia Recreate (single replica)
• Persistent Volumes: Armazenamento para banco de dados e imagens
• ConfigMap/Secrets: Configurações e credenciais da aplicação
```

### 2. Arquitetura da Aplicação - Clean Architecture

```
┌─────────────────────────────────────────────────────────────────────────────────────┐
│                              LANCHES CAIEIRAS - CLEAN ARCHITECTURE                  │
├─────────────────────────────────────────────────────────────────────────────────────┤
│ ┌─────────────────────────────────────────────────────────────────────────────────┐ │
│ │                              MÓDULO APP (Infraestrutura)                        │ │
│ │ ┌─────────────────────────────────────────────────────────────────────────────┐ │ │
│ │ │                           CAMADA DE APRESENTAÇÃO                            │ │ │
│ │ │                                                                             │ │ │
│ │ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐           │ │ │
│ │ │  │  REST API        │  │    WEBHOOKS      │  │    HANDLERS      │           │ │ │
│ │ │  │  Controllers     │  │                  │  │   (Inbound)      │           │ │ │
│ │ │  │ • Customer       │  │ • MercadoPago    │  │ • Customer       │           │ │ │
│ │ │  │ • FoodItem       │  │   Webhook        │  │ • FoodItem       │           │ │ │
│ │ │  │ • CustomerOrder  │  │                  │  │ • CustomerOrder  │           │ │ │
│ │ │  │ • KitchenOrder   │  │                  │  │ • KitchenOrder   │           │ │ │
│ │ │  │ • Payment        │  │                  │  │ • Payment        │           │ │ │
│ │ │  │ • Notification   │  │                  │  │ • Notification   │           │ │ │
│ │ │  └──────────────────┘  └──────────────────┘  └──────────────────┘           │ │ │
│ │ └─────────────────────────────────────────────────────────────────────────────┘ │ │
│ │                                    │                                            │ │
│ │ ┌─────────────────────────────────────────────────────────────────────────────┐ │ │
│ │ │                          CAMADA DE INTEGRAÇÃO                               │ │ │
│ │ │                                                                             │ │ │
│ │ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐           │ │ │
│ │ │  │   INTEGRAÇÕES    │  │  DATASOURCES     │  │    STORAGE       │           │ │ │
│ │ │  │                  │  │                  │  │                  │           │ │ │
│ │ │  │ • MercadoPago    │  │ • PostgreSQL     │  │ • File System    │           │ │ │
│ │ │  │   API Client     │  │   Repositories   │  │   (Images)       │           │ │ │
│ │ │  │ • External APIs  │  │ • JPA Entities   │  │                  │           │ │ │
│ │ │  │                  │  │ • Database       │  │                  │           │ │ │
│ │ │  │                  │  │   Config         │  │                  │           │ │ │
│ │ │  └──────────────────┘  └──────────────────┘  └──────────────────┘           │ │ │
│ │ └─────────────────────────────────────────────────────────────────────────────┘ │ │
│ └─────────────────────────────────────────────────────────────────────────────────┘ │
│                                    │                                                │
│                              INTERFACE BOUNDARY                                     │
│                                    │                                                │
│ ┌─────────────────────────────────────────────────────────────────────────────────┐ │
│ │                            MÓDULO CORE (Regras de Negócio)                      │ │
│ │ ┌─────────────────────────────────────────────────────────────────────────────┐ │ │
│ │ │                              ADAPTERS LAYER                                 │ │ │
│ │ │                                                                             │ │ │
│ │ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐           │ │ │
│ │ │  │   CONTROLLERS    │  │    GATEWAYS      │  │   PRESENTERS     │           │ │ │
│ │ │  │  (Input Ports)   │  │ (Output Ports)   │  │  (Output Ports)  │           │ │ │
│ │ │  │                  │  │                  │  │                  │           │ │ │
│ │ │  │ • Customer       │  │ • Customer       │  │ • Customer       │           │ │ │
│ │ │  │ • FoodItem       │  │ • FoodItem       │  │ • FoodItem       │           │ │ │
│ │ │  │ • CustomerOrder  │  │ • CustomerOrder  │  │ • CustomerOrder  │           │ │ │
│ │ │  │ • KitchenOrder   │  │ • KitchenOrder   │  │ • KitchenOrder   │           │ │ │
│ │ │  │ • Payment        │  │ • Payment        │  │ • Payment        │           │ │ │
│ │ │  │ • Notification   │  │ • Notification   │  │ • Notification   │           │ │ │
│ │ │  └──────────────────┘  └──────────────────┘  └──────────────────┘           │ │ │
│ │ └─────────────────────────────────────────────────────────────────────────────┘ │ │
│ │                                    │                                            │ │
│ │ ┌─────────────────────────────────────────────────────────────────────────────┐ │ │
│ │ │                           APPLICATION LAYER                                 │ │ │
│ │ │                            (Use Cases)                                      │ │ │
│ │ │                                                                             │ │ │
│ │ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐           │ │ │
│ │ │  │    CUSTOMER      │  │    FOOD ITEM     │  │ CUSTOMER ORDER   │           │ │ │
│ │ │  │   Use Cases      │  │   Use Cases      │  │   Use Cases      │           │ │ │
│ │ │  │                  │  │                  │  │                  │           │ │ │
│ │ │  │ • Create         │  │ • Create         │  │ • Create         │           │ │ │
│ │ │  │ • Get            │  │ • Get            │  │ • Get            │           │ │ │
│ │ │  │ • Update         │  │ • Update         │  │ • Update         │           │ │ │
│ │ │  │ • Delete         │  │ • Delete         │  │ • Delete         │           │ │ │
│ │ │  │                  │  │ • Image Upload   │  │                  │           │ │ │
│ │ │  └──────────────────┘  └──────────────────┘  └──────────────────┘           │ │ │
│ │ │                                                                             │ │ │
│ │ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐           │ │ │
│ │ │  │ KITCHEN ORDER    │  │    PAYMENT       │  │  NOTIFICATION    │           │ │ │
│ │ │  │   Use Cases      │  │   Use Cases      │  │   Use Cases      │           │ │ │
│ │ │  │                  │  │                  │  │                  │           │ │ │
│ │ │  │ • Create         │  │ • Create QR      │  │ • Create         │           │ │ │
│ │ │  │ • Get            │  │ • Get Status     │  │ • Get            │           │ │ │
│ │ │  │ • Update Status  │  │ • Update Status  │  │                  │           │ │ │
│ │ │  │                  │  │ • MercadoPago    │  │                  │           │ │ │
│ │ │  └──────────────────┘  └──────────────────┘  └──────────────────┘           │ │ │
│ │ └─────────────────────────────────────────────────────────────────────────────┘ │ │
│ │                                    │                                            │ │
│ │ ┌─────────────────────────────────────────────────────────────────────────────┐ │ │
│ │ │                             DOMAIN LAYER                                    │ │ │
│ │ │                       (Entidades de Negócio)                                │ │ │
│ │ │                                                                             │ │ │
│ │ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐           │ │ │
│ │ │  │    CUSTOMER      │  │    FOOD ITEM     │  │ CUSTOMER ORDER   │           │ │ │
│ │ │  │                  │  │                  │  │                  │           │ │ │
│ │ │  │ • Customer       │  │ • FoodItem       │  │ • CustomerOrder  │           │ │ │
│ │ │  │ • CustomerCPF    │  │ • FoodItemImage  │  │ • OrderFoodItem  │           │ │ │
│ │ │  │ • CustomerEmail  │  │                  │  │ • OrderCustomer  │           │ │ │
│ │ │  │ • DocumentNumber │  │                  │  │                  │           │ │ │
│ │ │  └──────────────────┘  └──────────────────┘  └──────────────────┘           │ │ │
│ │ │                                                                             │ │ │
│ │ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐           │ │ │
│ │ │  │ KITCHEN ORDER    │  │    PAYMENT       │  │  NOTIFICATION    │           │ │ │
│ │ │  │                  │  │                  │  │                  │           │ │ │
│ │ │  │ • KitchenOrder   │  │ • Payment        │  │ • Notification   │           │ │ │
│ │ │  │ • OrderFoodItem  │  │ • PaymentQR      │  │                  │           │ │ │
│ │ │  │                  │  │   (MercadoPago)  │  │                  │           │ │ │
│ │ │  └──────────────────┘  └──────────────────┘  └──────────────────┘           │ │ │
│ │ └─────────────────────────────────────────────────────────────────────────────┘ │ │
│ └─────────────────────────────────────────────────────────────────────────────────┘ │
│ ┌─────────────────────────────────────────────────────────────────────────────────┐ │
│ │                              INFRASTRUCTURE                                     │ │
│ │                                                                                 │ │
│ │  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐               │ │
│ │  │   POSTGRESQL     │  │  FILE SYSTEM     │  │  MERCADOPAGO     │               │ │
│ │  │    DATABASE      │  │    STORAGE       │  │      API         │               │ │
│ │  │                  │  │                  │  │(Sistema Externo) │               │ │
│ │  │ • Tabelas        │  │ • /app/images    │  │ • OAuth Token    │               │ │
│ │  │ • Relacionamentos│  │ • Upload/Download│  │ • QR Code        │               │ │
│ │  │ • Transações     │  │ • CRUD Files     │  │ • Webhooks       │               │ │
│ │  └──────────────────┘  └──────────────────┘  └──────────────────┘               │ │
│ └─────────────────────────────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────────────────────────────┘

Princípios da Clean Architecture aplicados:
• Independência de Frameworks: Regras de negócio não dependem de frameworks externos
• Testabilidade: Cada camada pode ser testada independentemente
• Independência da UI: Interface pode mudar sem afetar regras de negócio  
• Independência do Banco de Dados: Regras não conhecem detalhes de persistência
• Independência de Agentes Externos: Regras não dependem de APIs externas
• Regra de Dependência: Dependências apontam sempre para dentro (domínio)
```

**Detalhamento das Camadas:**

- **CORE (Regras de Negócio):**
   - **Domain**: Entidades de negócio puras (Customer, FoodItem, CustomerOrder, etc.)
   - **Application**: Casos de uso que orquestram as regras de negócio
   - **Adapters**: Implementações das interfaces (Controllers, Gateways, Presenters)

- **APP (Infraestrutura):**
   - **Handlers**: Controladores REST que recebem tratam exceções
   - **Integrations**: Clientes para APIs internas e externas (MercadoPago)
   - **Datasources**: Acesso a dados (PostgreSQL JPA, File System)
   - **Webhooks**: Endpoints para receber callbacks externos


### 3. Arquitetura Funcional
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Example HTML</title>
</head>
<body>
        <iframe src="docs/funcional/lanches-caieiras-arquitetura-fincional.html" width="800" height="700">
        Diagramas de Arquitetura Funcional
        </iframe>
</body>
</html>


## Contrato da API

O contrato da API está disponível no arquivo `lanches-caieiras-api-v2.yaml` no diretório `/docs/api/`

Para visualizar e interagir com a documentação da API, siga os passos:

1. Acesse o [Swagger Editor](https://editor.swagger.io/).
2. Clique em "File" > "Import File" e selecione o arquivo `lanches-caieiras-api-v2.yamll` deste repositório.

Assim, você poderá visualizar e testar o contrato da API de forma interativa.

## Testes via Postman
Para testar a API, você pode usar o Postman. O arquivo de coleção do Postman está disponível no diretório `/docs/api/`.
1. Importe o arquivo `lanches-caieiras-f2-collection.json` no Postman.
2. Certifique-se de que o servidor esteja rodando.
3. A collection está organizada por Domínios:
    - Cliente
    - Items de Alimentação
    - Pedido do Cliente
    - Pagamento
    - Ordem de Preparo
    - Notificações
4. Execute as requisições na seguinte ordem
   - Criação de Clientes
   - Criação de Items de Alimentação
   - Criar Pedido do Cliente
   - Processar pagamento
   - Atualizar Preparo
   - Finalizar Pedido do Cliente