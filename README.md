# E-commerce Java Backend

## Overview
A fully functional **e-commerce backend** built with **Java 17** and **Spring Boot 3.3**, featuring JWT authentication, PostgreSQL database, RESTful APIs, and a scalable microservices architecture.

---

## Tech Stack
- **Java 17**
- **Spring Boot 3.3**
- **Spring Security (JWT Authentication)**
- **PostgreSQL**
- **Hibernate (JPA)**
- **Docker & Docker Compose**

---

## Features
✅ User Authentication & Authorization (JWT)  
✅ Product Management (CRUD Operations)  
✅ Order Management & Checkout Process  
✅ Secure REST APIs  
✅ Role-based Access Control  
✅ PostgreSQL Database Integration  
✅ Dockerized Deployment  

---

## Cloning the Repository
To clone the repository, run:
```sh
git clone https://github.com/HNLad/ecommerce-java.git
```

---

## Setup & Installation
### 1. Install Dependencies
Ensure you have **Java 17**, **Maven**, and **PostgreSQL** installed.

### 2. Configure Database
Update `application.properties` with your PostgreSQL credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application
```sh
mvn clean install
java -jar target/ecommerce-java.jar
```

---

## API Endpoints
### Authentication
- **User Login**
  ```
  POST /api/auth/login
  ```
- **User Registration**
  ```
  POST /api/auth/register
  ```

### Product Management
- **Get All Products**
  ```
  GET /api/products
  ```
- **Add a Product** *(Admin only)*
  ```
  POST /api/products
  ```

### Order Management
- **Place an Order**
  ```
  POST /api/orders
  ```
- **Get Order Details**
  ```
  GET /api/orders/{id}
  ```

---

## Docker Deployment
To run the service with Docker, use:
```sh
docker-compose up --build
```

---

## Contributing
1. Fork the repository.
2. Create a new feature branch.
3. Commit and push changes.
4. Open a Pull Request.

---
