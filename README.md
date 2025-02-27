# 🚀 Ferramenta PEWS Back - Spring Boot + Docker

## 📌 Sobre o Projeto
Este projeto é um backend desenvolvido em Spring Boot e Docker para facilitar a implantação e execução em qualquer ambiente sem necessidade de configuração manual do Java ou Maven. O sistema gerencia informações de pacientes, profissionais de saúde e pontuações relacionadas ao PEWS (Pediatric Early Warning Score).

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Maven**
- **Docker**
- **PostgreSQL**

## 📂 Estrutura do Projeto
```
/
├── src/
│   ├── main/java/ferramenta_pews_back/
│   │   ├── FerramentaPewsBackApplication.java  # Classe principal
│   │   ├── Controller/                         # Controladores REST
│   │   ├── DTOs/                               # Objetos de Transferência de Dados
│   │   ├── Entities/                           # Modelos do banco de dados
│   │   ├── Exception/                          # Classes para tratamento de exceções
│   │   ├── Handler/                            # Manipuladores de exceções e eventos
│   │   ├── Respositories/                      # Interfaces de acesso ao banco de dados
│   │   ├── Service/                            # Regras de negócio e lógica de serviço
│   │   └── Tools/                              # Utilitários e ferramentas auxiliares
├── pom.xml  # Gerenciamento de dependências Maven
├── Dockerfile  # Configuração do container Docker
└── README.md  # Documentação do projeto
```

## 🚀 Como Rodar com Docker

### 📌 Pré-requisitos
Certifique-se de ter o [Docker](https://www.docker.com/get-started) instalado.

### 🔧 Construindo e Executando a Aplicação
1. **Construir a imagem Docker:**
   ```sh
   docker build -t api-sistema-pews .
   ```
2. **Executar o container:**
   ```sh
   docker run -p 8080:8080 api-sistema-pews
   ```


## 📄 API Endpoints

| Método | Endpoint                    | Descrição                                   | Parâmetros |
|---------|----------------------------|--------------------------------|------------|
| GET     | `/api/patients/uuid`       | Retorna um paciente pelo UUID  | `uuid` (UUID) |
| GET     | `/api/patients/listAll`    | Retorna lista paginada de pacientes | `pageNo` (int, opcional), `pageSize` (int, opcional), `name` (string, opcional) |
| GET     | `/api/patients/listLatestScores` | Retorna lista de pacientes com as últimas pontuações | `pageNo` (int, opcional), `pageSize` (int, opcional), `sortBy` (string, opcional), `sortDirection` (string, opcional) |
| POST    | `/api/patients`            | Cria um novo paciente         | Body: `PatientPostDTO` |
| GET     | `/api/healthstaff`         | Retorna um profissional pelo username | `username` (string) |
| GET     | `/api/healthstaff/uuid`    | Retorna um profissional pelo UUID | `uuid` (UUID) |
| GET     | `/api/healthstaff/listAll` | Retorna lista de profissionais | Nenhum |
| POST    | `/api/healthstaff/login`   | Realiza login de um profissional | Body: `LoginRequestDTO` |
| POST    | `/api/healthstaff`         | Cria um novo profissional     | Body: `HealthStaffPostDTO` |
| PUT     | `/api/healthstaff`         | Atualiza um profissional      | Body: `HealthStaffPutDTO` |
| DELETE  | `/api/healthstaff`         | Deleta um profissional        | `uuid` (UUID) |
| GET     | `/api/scores`              | Retorna lista de pontuações   | Nenhum |
| POST    | `/api/score`               | Registra uma nova pontuação  | Body: `ScorePostDTO` |
| POST    | `/api/workspaces`          | Cria um novo workspace        | Nenhum |
