# Sistema de Agendamento de Consultas

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos, com o objetivo de praticar os conceitos da matéria através de um sistema simples em Java com persistência em MySQL.

## Sobre o projeto

O sistema permite cadastrar pacientes e médicos e agendar consultas entre eles, tudo via terminal. A ideia foi organizar o código em camadas (model, DAO e service) e usar JDBC para salvar os dados no banco.

## Funcionalidades

- CRUD de Pacientes (cadastrar, listar, buscar, alterar, excluir)
- CRUD de Médicos (cadastrar, listar, buscar, alterar, excluir)
- CRUD de Consultas (agendar, listar, buscar, alterar, cancelar)
- Persistência no MySQL usando JDBC
- Relacionamento entre Paciente, Médico e Consulta
- Validações básicas (campos obrigatórios, CPF/CRM únicos)

## Tecnologias

- Java SE
- MySQL
- JDBC
- XAMPP
- VS Code

## Estrutura do projeto

```
appdb/
├── .vscode/
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── bin/
└── src/
    ├── model/
    │   ├── Paciente.java
    │   ├── Medico.java
    │   └── Consulta.java
    ├── service/
    │   └── Agenda.java
    ├── dao/
    │   ├── ConexaoDB.java
    │   ├── PacienteDAO.java
    │   ├── MedicoDAO.java
    │   └── ConsultaDAO.java
    └── Main.java
```

## Relacionamento entre as entidades

- Um paciente pode ter várias consultas
- Um médico pode ter várias consultas
- Cada consulta pertence a um paciente e a um médico

## Pré-requisitos

- JDK 17 ou superior
- XAMPP com o MySQL ativo
- Driver JDBC na pasta `lib/`

## Banco de dados

Script para criar o banco e as tabelas:

```sql
CREATE DATABASE agenda_consultas;
USE agenda_consultas;

CREATE TABLE pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT NOT NULL,
    data_consulta DATE NOT NULL,
    hora_consulta TIME NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE,
    FOREIGN KEY (medico_id) REFERENCES medicos(id) ON DELETE CASCADE
);
```

## Como executar

Compilar:

```bash
cd appdb/src
javac -cp ".;../lib/*" -d ../bin *.java model/*.java service/*.java dao/*.java
```

Executar:

```bash
java -cp "../bin;../lib/*" Main
```

Em Linux/macOS, troque o `;` por `:` no classpath.

## Menu do sistema

```
===================================
  SISTEMA DE AGENDAMENTO
===================================
1 - Pacientes
2 - Médicos
3 - Consultas
0 - Sair
```

## Autor

Yasmin — [@Yaswsxz](https://github.com/Yaswsxz)

Disciplina: Programação Orientada a Objetos
Professor: Cesar Malvezi
Agosto/2026