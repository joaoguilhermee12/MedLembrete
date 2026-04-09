# 💊 MedLembrete

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.13-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Version](https://img.shields.io/badge/version-1.0.0-informational)

## 📋 Descrição do Problema Real

O esquecimento de medicamentos é um problema sério que afeta milhões de pessoas, especialmente idosos e pacientes com doenças crônicas. A falta de controle sobre horários, doses e duração do tratamento pode causar complicações graves à saúde, reinternações hospitalares e piora de doenças. Muitos pacientes tomam múltiplos medicamentos por dia e não possuem uma forma organizada de acompanhar seu tratamento.

## 💡 Proposta da Solução

O **MedLembrete** é uma API REST desenvolvida em Java com Spring Boot que permite o cadastro e controle de medicamentos vinculados a um usuário. O sistema registra nome do medicamento, horário, data de início e fim do tratamento, quantidade de doses e permite marcar quantas doses já foram tomadas no dia. Um administrador pode gerenciar os usuários do sistema, incluindo ativação e desativação de contas.

## 👥 Público-Alvo

- Pacientes com doenças crônicas que tomam múltiplos medicamentos
- Idosos que precisam de auxílio no controle do tratamento
- Cuidadores que gerenciam a medicação de terceiros
- Familiares que acompanham o tratamento de parentes

## ⚙️ Funcionalidades Principais

### Usuários
- Cadastro de usuários com nome, e-mail, CPF e senha
- Listagem de todos os usuários
- Atualização de dados do usuário
- Desativação e reativação de conta (apenas ADMIN)
- Exclusão de usuário (apenas ADMIN)

### Medicamentos
- Cadastro de medicamento vinculado ao usuário
- Listagem de medicamentos do usuário
- Atualização de dados do medicamento
- Marcar dose como tomada (incrementa contador de doses tomadas)
- Exclusão de medicamento

### Segurança
- Controle de perfis: USER e ADMIN
- Apenas ADMIN pode desativar, reativar e excluir usuários
- Admin padrão criado automaticamente na inicialização do sistema

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 17+ | Linguagem principal |
| Spring Boot | 3.5.13 | Framework web |
| Spring Data JPA | 3.5.13 | Persistência de dados |
| Spring Validation | 3.5.13 | Validação de campos |
| PostgreSQL | 15+ | Banco de dados principal |
| H2 Database | 2.3.x | Banco de dados para testes |
| Lombok | 1.18.44 | Redução de boilerplate |
| SpringDoc OpenAPI | 2.8.8 | Documentação Swagger |
| Maven | 3.x | Gerenciamento de dependências |

## 📁 Estrutura do Projeto

```
src/
└── main/
    └── java/com/joaoguilhermee/MedLembrete/
        ├── Controller/
        │   ├── MedicineController.java
        │   └── UserController.java
        ├── Exception/
        │   └── ResourceNotFoundException.java
        ├── Handler/
        │   └── GlobalExceptionHandler.java
        ├── Model/
        │   ├── DTO/
        │   │   └── UserSummaryDTO.java
        │   ├── Medicine.java
        │   ├── Role.java
        │   └── User.java
        ├── Repository/
        │   ├── MedicineRepository.java
        │   └── UserRepository.java
        ├── Service/
        │   ├── MedicineService.java
        │   └── UserService.java
        ├── DataInitializer.java
        └── MedLembreteApplication.java
```

## 🔧 Pré-requisitos

- Java 17 ou superior
- Maven 3.x
- PostgreSQL 15 ou superior

## 🚀 Instruções de Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/joaoguilhermee12/MedLembrete.git
cd MedLembrete
```

### 2. Configure o banco de dados

Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE membres;
```

### 3. Configure as credenciais

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/membres
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 4. Instale as dependências

```bash
mvn clean install -DskipTests
```

## ▶️ Instruções de Execução

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

### Usuário Admin padrão

Na primeira execução, o sistema cria automaticamente um usuário administrador:

```
E-mail: admin@medLembrete.com
Senha:  admin123
```

> ⚠️ Recomenda-se alterar a senha do admin após o primeiro acesso.

## 📖 Documentação da API (Swagger)

Após iniciar a aplicação, acesse a documentação interativa:

```
http://localhost:8080/swagger-ui/index.html
```

### Principais Rotas

#### Usuários
| Método | Rota | Descrição | Permissão |
|---|---|---|---|
| POST | /users | Criar usuário | Público |
| GET | /users | Listar todos os usuários | Público |
| GET | /users/{id} | Buscar usuário por ID | Público |
| PUT | /users/{id} | Atualizar usuário | Público |
| DELETE | /users/{id}?adminId={id} | Excluir usuário | ADMIN |
| PATCH | /users/{id}/deactivate?adminId={id} | Desativar usuário | ADMIN |
| PATCH | /users/{id}/reactivate?adminId={id} | Reativar usuário | ADMIN |

#### Medicamentos
| Método | Rota | Descrição |
|---|---|---|
| POST | /users/{userId}/medicamentos | Cadastrar medicamento |
| GET | /users/{userId}/medicamentos | Listar medicamentos do usuário |
| PUT | /users/{userId}/medicamentos/{id} | Atualizar medicamento |
| DELETE | /users/{userId}/medicamentos/{id} | Excluir medicamento |
| PATCH | /users/{userId}/medicamentos/{id}/tomado | Marcar dose como tomada |

### Exemplos de Uso

#### Criar usuário
```json
POST /users
{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "123456",
    "CPF": "12345678901"
}
```

#### Cadastrar medicamento
```json
POST /users/1/medicamentos
{
    "nome": "Dipirona",
    "horario": "08:00, 14:00, 20:00",
    "diaInicio": "2026-04-08",
    "diaFinal": "2026-04-15",
    "doses": 9,
    "dosesPorDia": 3
}
```

#### Marcar dose como tomada
```
PATCH /users/1/medicamentos/1/tomado
```
Cada chamada incrementa `dosesTomadas` em +1. Quando `dosesTomadas == dosesPorDia`, retorna erro informando que todas as doses do dia já foram tomadas.

#### Desativar usuário (apenas ADMIN)
```
PATCH /users/2/deactivate?adminId=7
```

## 🧪 Instruções para Rodar os Testes

```bash
mvn test
```

Os testes utilizam o banco H2 em memória automaticamente, sem necessidade de PostgreSQL configurado.

Para rodar um teste específico:

```bash
mvn test -Dtest=MedLembreteApplicationTests
```

## 🔍 Instruções para Rodar o Lint (Checkstyle)

```bash
mvn checkstyle:check
```

## 📦 Versão

**1.0.0**

- `1` — versão maior (MAJOR): estrutura principal da API
- `0` — funcionalidades menores (MINOR): sem breaking changes pendentes
- `0` — correções (PATCH): versão estável inicial

A versão está declarada no `pom.xml`:
```xml
<version>1.0.0</version>
```

## 🔄 GitHub Actions / CI

O projeto possui pipeline de integração contínua configurada em `.github/workflows/maven.yml`. A pipeline é executada automaticamente a cada `push` ou `pull request` na branch `main` e realiza:

1. Configuração do ambiente com Java 17
2. Instalação das dependências via Maven
3. Execução dos testes automatizados com banco H2

## 📝 Dependências

Todas as dependências estão declaradas no arquivo `pom.xml` na raiz do projeto.

## 👤 Autor

**João Guilherme**
- GitHub: [@joaoguilhermee12](https://github.com/joaoguilhermee12)

## 🔗 Repositório

[https://github.com/joaoguilhermee12/MedLembrete](https://github.com/joaoguilhermee12/MedLembrete)
