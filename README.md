<h1 align="center">Domus 🏡</h1>

<p align="center">
  Plataforma de gestão imobiliária para controle de portfólios, vistorias e propriedades.<br>
  <b>Domain-Driven Design (DDD)</b> • <b>Microsserviços</b> • <b>GraphQL</b> • <b>Kubernetes</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/GraphQL-API-ff4081?style=flat&logo=graphql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-Container-2496ed?style=flat&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/Kubernetes-Orchestration-326ce5?style=flat&logo=kubernetes&logoColor=white"/>
  <img src="https://img.shields.io/badge/RabbitMQ-Messaging-ff6600?style=flat&logo=rabbitmq&logoColor=white"/>
</p>

---

## 📖 Visão Geral

**Domus** é uma plataforma para o setor imobiliário, desenvolvida para simplificar a gestão de portfólios de imobiliárias. A solução contempla o controle de funcionários, cadastro de clientes, gerenciamento de propriedades, realização de vistorias e outras rotinas essenciais do setor.

## ✨ Funcionalidades

- ✅ Gestão de funcionários e clientes
- ✅ Cadastro e gerenciamento de propriedades
- ✅ Controle de vistorias imobiliárias
- ✅ Integração entre módulos via microsserviços
- ✅ Interface única via GraphQL Gateway

## ⚙️ Tecnologias

| Categoria            | Tecnologias                              |
|----------------------|------------------------------------------|
| **Backend**          | Java 21, GraphQL Gateway                 |
| **Arquitetura**      | Microsserviços, RabbitMQ, DDD, SOLID     |
| **Containers**       | Docker, Kubernetes                       |
| **Observabilidade**  | Grafana, Prometheus                      |
| **Qualidade de Código** | SonarQube                             |
| **Testes**           | JUnit, Mockito, Testcontainers           |

## 🛠️ Boas práticas e arquitetura

- 🧩 **Domain-Driven Design (DDD)** para modelagem do domínio
- 💡 Princípios **SOLID** aplicados no design do código
- 🛡️ Testes automatizados com **JUnit**, **Mockito** e **Testcontainers**
- 📊 Monitoramento de métricas e logs com **Grafana** e **Prometheus**

## 🚀 Como rodar o projeto

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/domus.git
cd domus

# Suba os serviços com Docker Compose (exemplo)
docker-compose up -d

# Acesse o sistema
http://localhost:8080
