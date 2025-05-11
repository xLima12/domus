# Domus

Domus é uma plataforma para o setor imobiliário, desenvolvida para simplificar a gestão de portfólios de imobiliárias. A solução contempla o controle de funcionários, cadastro de clientes, gerenciamento de propriedades, realização de vistorias e outras atividades essenciais do setor.

## ✨ Funcionalidades

- Gestão de funcionários e clientes
- Cadastro e gerenciamento de propriedades
- Controle de vistorias imobiliárias
- Integração entre módulos via microsserviços
- Interface única via GraphQL (gateway)

## ⚙️ Tecnologias

- **Backend**: Java 21
- **Arquitetura**: Microsserviços, GraphQL Gateway
- **Mensageria**: RabbitMQ
- **Containers & Orquestração**: Docker, Kubernetes
- **Observabilidade**: Grafana, Prometheus
- **Qualidade de Código**: SonarQube

## 🛠️ Boas práticas e arquitetura

- **Domain-Driven Design (DDD)** para modelagem do domínio
- Princípios **SOLID** aplicados no design do código
- Testes automatizados com **JUnit**, **Mockito** e **Testcontainers**
- Monitoramento de métricas e logs em tempo real

## 🚀 Como rodar o projeto

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/domus.git
cd domus

# Suba os serviços com Docker Compose (exemplo)
docker-compose up -d

# Acesse o sistema
http://localhost:8080
