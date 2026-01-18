# E-Commerce REST API

A Spring Boot-based e-commerce REST API with JWT authentication, role-based authorization, and comprehensive product management features including image upload capabilities.

## Features

### Authentication & Authorization
- JWT-based authentication
- Role-based access control (USER/ADMIN)
- Secure password encryption with BCrypt
- Default admin account initialization

### Core Functionality
- User management and registration
- Product catalog with categories
- Image upload and management for products
- Order and order item management (shopping cart)
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

## Project Structure

```
src/main/java/com/ahmed/e_commerce/
├── config/              # Security and JWT configuration
├── controllers/         # REST API endpoints
├── Dto/                 # Data Transfer Objects
├── Entity/              # JPA entities
├── exceptions/          # Global exception handling
├── mapper/              # Entity-DTO mappers
├── repository/          # Data access layer
└── service/             # Business logic layer
```

## Database Setup

1. Install PostgreSQL
2. Create database:
```sql
CREATE DATABASE e-commerce_DB;
```

3. Configure `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/e-commerce_DB
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Installation & Running

```bash
git clone https://github.com/A-h-m-e-d004/E-commerce.git
cd e-commerce
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login (returns JWT token)

### Products
- `GET /api/products` - Get all products (public)
- `GET /api/products/{id}` - Get product by ID (public)
- `GET /api/products/category/{categoryId}` - Get products by category (public)
- `POST /api/products` - Add new product (authenticated)
- `PUT /api/products/{id}` - Update product (authenticated)
- `DELETE /api/products/{id}` - Delete product (authenticated)
- `PUT /api/products/{productId}/quantity/update` - Update product quantity (authenticated)

### Categories
- `GET /api/category/all` - Get all categories
- `POST /api/category/create` - Create new category

### Images
- `POST /api/v1/images/image/upload` - Upload product images
- `GET /api/v1/images/image/download/{imageId}` - Download single image
- `GET /api/v1/images/image/downloadAll/{productId}` - Download all product images
- `DELETE /api/v1/images/image/delete/{imageId}` - Delete image

### Orders (USER role required)
- `POST /api/orders` - Create new order

### Order Items (Shopping Cart)
- `POST /api/orderItem` - Add item to order
- `GET /api/orderItem/{orderId}` - Get order items
- `PUT /api/orderItem/card/{cardId}/product/{productId}` - Update order item quantity
- `DELETE /api/orderItem/{cardId}` - Delete order item

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `DELETE /api/users/{id}` - Delete user

### Admin (ADMIN role required)
- `POST /admin/user/{id}/role` - Update user role

## Usage Examples

### Register User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

### Using JWT Token
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Upload Product Images
```bash
curl -X POST http://localhost:8080/api/v1/images/image/upload \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -F "productId=1" \
  -F "files=@image1.jpg" \
  -F "files=@image2.jpg"
```

## Default Admin Account

- **Username**: `admin`
- **Password**: `admin123`
- **Role**: `ADMIN`

## Data Models

### User
- ID (Long)
- Username (String, unique)
- Password (String, encrypted)
- Role (USER/ADMIN)
- Orders (List)

### Category
- ID (Long)
- Title (String)
- Products (List)

### Product
- ID (Long)
- Title (String)
- Description (String)
- Price (BigDecimal)
- Quantity (Integer)
- Category (Category)
- Images (List)
- Order Items (List)

### Image
- ID (Long)
- Image URL (String)
- File Name (String)
- File Type (String)
- Image (Blob)
- Product (Product)

### Order
- ID (Long)
- User (User)
- Order Items (List)

### OrderItem
- ID (Long)
- Order (Order)
- Product (Product)
- Quantity (Integer)
- Total Price (BigDecimal)

## Security Configuration

- **Public endpoints**: `/api/auth/**`, `GET /api/products/**`
- **User endpoints**: `/api/orders/**` (USER role)
- **Admin endpoints**: `/api/admin/**` (ADMIN role)
- All other endpoints require authentication

## Error Handling

Global exception handling for:
- Validation errors (400 Bad Request)
- Business logic errors (400 Bad Request)
- Runtime exceptions (400 Bad Request)
- General exceptions (400 Bad Request)


## Contact

Ahmed - [GitHub](https://github.com/A-h-m-e-d004)

Project Link: [https://github.com/A-h-m-e-d004/E-commerce](https://github.com/A-h-m-e-d004/E-commerce)
