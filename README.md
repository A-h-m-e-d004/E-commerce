# E-Commerce REST API

A Spring Boot-based e-commerce REST API with JWT authentication, role-based authorization, and comprehensive product management features.

## Features

### Authentication & Authorization
- JWT-based authentication
- Role-based access control (USER/ADMIN)
- Secure password encryption with BCrypt

### Core Functionality
- User management and registration
- Product catalog with categories
- Order and order item management
- Admin panel for user role management

### Security
- Spring Security integration
- JWT token validation
- Method-level security annotations
- Protected endpoints based on user roles

## Tech Stack

- **Backend**: Spring Boot 3.5.5
- **Database**: PostgreSQL
- **Security**: Spring Security + JWT
- **ORM**: Spring Data JPA/Hibernate
- **Validation**: Bean Validation
- **Build Tool**: Maven
- **Java Version**: 21
- ## Project Structure
- src/main/java/com/ahmed/e_commerce/
├── config/ # Security and JWT configuration
├── controllers/ # REST API endpoints
├── Dto/ # Data Transfer Objects
├── Entity/ # JPA entities
├── exceptions/ # Global exception handling
├── mapper/ # Entity-DTO mappers
├── repository/ # Data access layer
└── service/ # Business logic layer

## Database Setup

1. Install PostgreSQL
2. Create database:
sql
CREATE DATABASE e-commerce_DB;
spring.datasource.url=jdbc:postgresql://localhost:5432/e-commerce_DB
spring.datasource.username=your_username
spring.datasource.password=your_password

## Installation & Running

git clone <https://github.com/A-h-m-e-d004/E-commerce.git>
cd e-commerce

## API Endpoints
## Authentication
POST /api/auth/register - Register new user

POST /api/auth/login - User login

## Products
GET /api/products - Get all products (public)

GET /api/products/{id} - Get product by ID (public)

GET /api/products/category/{categoryId} - Get products by category (public)

POST /api/products - Add new product (authenticated)

PUT /api/products/{id} - Update product (authenticated)

DELETE /api/products/{id} - Delete product (authenticated)

## Categories
GET /api/category/all - Get all categories

POST /api/category/create - Create new category

## Orders (USER role required)
POST /api/orders - Create new order

POST /api/orderItem - Add item to order

GET /api/orderItem/{orderId} - Get order items

## Users
GET /api/users - Get all users

GET /api/users/{id} - Get user by ID

DELETE /api/users/{id} - Delete user

## Admin (ADMIN role required)
POST /admin/user/{id}/role - Update user role

## Authentication
Register User
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'


bash
Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'


bash
Using JWT Token
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"


bash
Default Admin Account
Username: admin

Password: admin123

Role: ADMIN

Data Models
User: ID, username, password, role (USER/ADMIN)

Category: ID, title

Product: ID, title, description, price, quantity, category

Order: ID, user, order items

OrderItem: ID, order, product, quantity, price

## Security Configuration
Public endpoints: /api/auth/**, GET /api/products/**

User endpoints: /api/orders/** (USER role)

Admin endpoints: /api/admin/** (ADMIN role)

All other endpoints require authentication

## Error Handling
Global exception handling for:

Validation errors (400 Bad Request)

Business logic errors (400 Bad Request)

Runtime exceptions (400 Bad Request)

General exceptions (400 Bad Request)

## Contributing
Fork the repository

Create a feature branch

Commit your changes

Push to the branch

Create a Pull Request

