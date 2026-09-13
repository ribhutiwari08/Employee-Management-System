# Inventory Management System

A backend inventory management application built with Java 17 and Spring Boot. It provides REST APIs for products, suppliers, stock updates, low-stock monitoring, and stock movement history.

## Tech Stack
- Java 17
- Spring Boot 3.5.5
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Jakarta Bean Validation
- Docker

## Features
- Product CRUD operations
- Supplier management
- Stock IN / OUT operations
- Stock validation to prevent negative inventory
- Low-stock product monitoring
- Stock movement history
- Layered architecture with Controller, Service, Repository, and Entity layers
- Environment-based database configuration for deployment

## Project Structure
```text
src/main/java/com/inventory/management/
├── InventoryManagementApplication.java
├── controller/
│   └── InventoryController.java
├── model/
│   ├── Product.java
│   ├── Supplier.java
│   └── StockMovement.java
├── repository/
│   ├── ProductRepository.java
│   ├── SupplierRepository.java
│   └── StockMovementRepository.java
└── service/
    └── InventoryService.java
```

## Database
Create a MySQL database named `inventory_db`, or let MySQL create it through the JDBC URL. Configure `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` as environment variables for deployment.

## Run locally
```bash
mvn clean package
mvn spring-boot:run
```

Application runs on `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |
| GET | `/api/suppliers` | Get all suppliers |
| POST | `/api/suppliers` | Create supplier |
| DELETE | `/api/suppliers/{id}` | Delete supplier |
| PATCH | `/api/products/{id}/stock?quantity=10&type=IN` | Add or remove stock |
| GET | `/api/products/low-stock` | Get products with low stock |
| GET | `/api/products/{id}/stock-history` | Get stock movement history |
| GET | `/api/health` | Health check |

### Product request example
```json
{
  "name": "Wireless Mouse",
  "sku": "WM-1001",
  "price": 799.0,
  "quantity": 25,
  "reorderLevel": 10
}
```

### Supplier request example
```json
{
  "name": "ABC Suppliers",
  "email": "supplier@example.com",
  "phone": "9876543210",
  "address": "New Delhi, India"
}
```

### Stock update
```text
PATCH /api/products/1/stock?quantity=5&type=IN
PATCH /api/products/1/stock?quantity=2&type=OUT
```
