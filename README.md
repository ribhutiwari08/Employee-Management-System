# Employee Management System

A secure RESTful Employee Management System built with **Spring Boot**, **Spring Security**, **JWT**, **Spring Data JPA**, and **MySQL**.

## Features

- User authentication with JWT
- Role-based access control
- Employee CRUD operations
- Secure RESTful APIs
- MySQL persistence using Spring Data JPA
- Layered architecture with Controller, Service, Repository and Security components

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA / Hibernate
- MySQL
- Maven

## API Overview

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/login` | Authenticate user and generate JWT |
| POST | `/api/employees` | Create employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

> Exact endpoint paths may vary depending on the final controller mappings.

## Project Structure

```text
src/main/java/
└── ...
    ├── controller/
    ├── service/
    ├── repository/
    ├── entity/
    ├── security/
    └── config/
```

## Configuration

Create a local MySQL database and configure the connection in `application.properties` or environment variables.

Never commit real database passwords, JWT secrets, API keys, or other credentials. Use environment variables or a local untracked configuration file instead.

## Run Locally

```bash
mvn clean install
mvn spring-boot:run
```

The application can then be tested with Postman or any REST client.

## Resume Description

Built an Employee Management System using Spring Boot, Spring Security, and JWT, implementing role-based access control and RESTful CRUD APIs with MySQL persistence via Spring Data JPA.
