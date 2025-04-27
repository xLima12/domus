# Guia de Contribuição para o Domus

Obrigado por considerar contribuir para o Domus!

A seguir estão algumas diretrizes para ajudar a mantermos o projeto organizado, de alta qualidade e acolhedor.

## Como Contribuir

1. Faça um fork do projeto e clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/domus.git
    ```
2. Crie uma branch para sua contribuição:
    ```bash
    git checkout -b feature/nome-da-feature
    ```
3. Siga o padrão de commits (Conventional Commits):
    - `feat:` para novas funcionalidades.
    - `fix:` para correções de bugs.
    - `chore:` para mudanças menores ou manutenção.
    - `docs:` para documentação.
    - `test:` para testes.

4. Certifique-se de:
    - Passar todos os testes (`./mvn test`).
    - O SonarQube estar sem problemas críticos.
    - Seguir as boas práticas de Clean Code e Java.

5. Abra uma Pull Request (PR) clara e descritiva no GitHub.

## Padrões de Código

- Java 21 + Spring Boot 3.
- Clean Architecture e SOLID Principles.
- Testes unitários e de integração são obrigatórios para novas features.
- Análise contínua com SonarQube para qualidade de código.
- Observabilidade mínima configurada (Prometheus + Grafana).

## Comunicação

- Seja respeitoso e paciente nas revisões de PRs.
- Prefira sempre soluções simples e claras.

---

**Muito obrigado pela sua contribuição!**  
Juntos vamos construir o melhor sistema de Gestão de Vistorias para Imobiliárias.