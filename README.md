# Employee Management System

A working Spring Boot REST API for employee management using Spring Security, JWT, Spring Data JPA and MySQL.

## Features
- Employee CRUD APIs
- MySQL persistence with JPA/Hibernate
- JWT token authentication
- Stateless Spring Security
- Role-based authorization: ADMIN can delete employees
- Validation using Jakarta Bean Validation

## Tech Stack
Java 17, Spring Boot 3, Spring Security, JWT, Spring Data JPA, Hibernate, MySQL, Maven

## Project Structure
```text
src/main/java/com/employee/management/
├── EmployeeManagementApplication.java
├── controller/
│   ├── AuthController.java
│   └── EmployeeController.java
├── model/
│   ├── Employee.java
│   └── Role.java
├── repository/
│   └── EmployeeRepository.java
├── service/
│   └── EmployeeService.java
└── security/
    ├── JwtAuthenticationFilter.java
    ├── JwtService.java
    └── SecurityConfig.java
```

## Database setup
Create a MySQL database named `employee_db`, then set your local MySQL password in `src/main/resources/application.properties`. Do not commit real credentials.

## Run
```bash
mvn clean install
mvn spring-boot:run
```

Server: `http://localhost:8080`

## Authentication
Request a token:
```text
POST /api/auth/login?username=admin
```

Use the returned token on protected requests:
```text
Authorization: Bearer <token>
```

## Employee APIs
| Method | Endpoint | Access |
|---|---|---|
| POST | `/api/employees` | Authenticated |
| GET | `/api/employees` | Authenticated |
| GET | `/api/employees/{id}` | Authenticated |
| PUT | `/api/employees/{id}` | Authenticated |
| DELETE | `/api/employees/{id}` | ADMIN |

Example request body:
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "department": "Engineering",
  "designation": "Software Developer",
  "role": "USER"
}
```

## Resume Description
Built an Employee Management System using Spring Boot, Spring Security, and JWT, implementing role-based access control and RESTful CRUD APIs with MySQL persistence via Spring Data JPA.