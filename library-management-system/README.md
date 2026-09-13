# Library Management System

Spring Boot REST API for managing books, members and borrowing transactions.

## Tech Stack
- Java 17
- Spring Boot 3.5.5
- Spring Data JPA / Hibernate
- MySQL
- Spring Security
- JWT (JJWT)
- Maven

## Features
- Book and member management
- Proper JPA `@ManyToOne` relationships between BorrowTransaction, Book and Member
- JWT authentication
- ADMIN / MEMBER role-based access
- Book availability validation before borrowing
- Automatic 14-day due date
- Overdue status and overdue-day calculation
- Return-book workflow that makes the book available again

## Run
1. Create/use MySQL locally. The configured database is `library_db`.
2. Edit `src/main/resources/application.properties` and replace `CHANGE_ME` with your MySQL password.
3. Run:
```bash
mvn spring-boot:run
```

## Authentication
Register a member:
```http
POST /api/auth/register
Content-Type: application/json

{"name":"John","email":"john@example.com","password":"password","role":"MEMBER"}
```
Login:
```http
POST /api/auth/login?email=john@example.com&password=password
```
Use the returned token as:
```http
Authorization: Bearer <token>
```

## APIs
| Method | Endpoint | Role |
|---|---|---|
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public |
| GET | `/api/books` | Authenticated |
| POST | `/api/admin/books` | ADMIN |
| GET | `/api/admin/members` | ADMIN |
| POST | `/api/admin/members` | ADMIN |
| POST | `/api/member/borrow/{bookId}/{memberId}` | MEMBER/ADMIN |
| POST | `/api/member/return/{transactionId}` | MEMBER/ADMIN |
| GET | `/api/member/transactions` | MEMBER/ADMIN |

## Business Rules
- A book cannot be borrowed when `available=false`.
- Borrowing sets `available=false` and creates a 14-day due date.
- Returning a book sets `returnedAt` and `available=true`.
- A transaction is overdue when the current date is after its due date and it has not been returned.
- `getOverdueDays()` calculates the number of overdue days.

## Security Note
The JWT secret is currently a development value in source for demonstration. For production, move it to an environment variable or secret manager. Do not commit real credentials.
