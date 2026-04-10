# Changelog

Todas as mudanças notáveis deste projeto serão documentadas aqui.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Versionamento Semântico](https://semver.org/lang/pt-BR/).

---

## [1.0.0] - 2026-04-10

### Adicionado
- Cadastro de usuários com nome, e-mail, CPF e senha
- Listagem de todos os usuários
- Atualização de dados do usuário
- Exclusão de usuário (apenas ADMIN)
- Desativação e reativação de conta (apenas ADMIN)
- Cadastro de medicamentos vinculados ao usuário
- Listagem de medicamentos por usuário
- Atualização de dados do medicamento
- Exclusão de medicamento
- Controle de doses tomadas por dia com limite máximo
- Perfis de acesso: USER e ADMIN
- Admin padrão criado automaticamente na inicialização
- Validação de campos obrigatórios com Bean Validation
- CPF único por usuário com mensagem de erro amigável
- Tratamento global de exceções com mensagens claras
- Documentação interativa com Swagger/OpenAPI
- Testes automatizados com JUnit 5 e banco H2 em memória
- Análise estática de código com Checkstyle
- Pipeline de CI com GitHub Actions rodando testes e lint

### Tecnologias
- Java 17
- Spring Boot 3.5.13
- PostgreSQL
- Lombok
- Maven 1.0.0