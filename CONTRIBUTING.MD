# Como Contribuir

Obrigado por considerar contribuir com o MedLembrete!

## Pré-requisitos
- Java 17+
- Maven 3.x
- PostgreSQL

## Como rodar o projeto
1. Clone o repositório
2. Configure o banco no `application.properties`
3. Execute `mvn spring-boot:run`

## Como rodar os testes
```bash
mvn test
```

## Como rodar o lint
```bash
mvn checkstyle:check
```

## Padrões do projeto
- Código em inglês
- Mensagens de erro em português
- Commits no formato: `feat:`, `fix:`, `test:`, `chore:`