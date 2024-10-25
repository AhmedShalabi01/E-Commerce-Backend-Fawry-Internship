# E-Commerce-Fawry-Internship

## Project Overview
This E-Commerce platform is designed as a **microservices-based** architecture, built with the **Spring Framework**. The application is modularized into distinct services, each responsible for a specific domain function, ensuring scalability, flexibility, and maintainability. This structure allows for individual services to be developed, deployed, and scaled independently.

## Microservices Architecture
The project consists of the following microservices:

### 1. Bank Service
Handles payment and transaction processing, interacting with payment gateways and managing bank-related operations for secure payment processing.

### 2. Coupon Service
Manages discount codes and promotional coupons, allowing users to apply them during the checkout process to receive discounts.

### 3. Service Discovery Server
Facilitates microservices discovery using **Spring Cloud Eureka**, allowing each service to dynamically locate and communicate with other services.

### 4. Gateway Service
Acts as a single entry point for all client requests, routing them to the appropriate microservices. This service includes security, rate limiting, and request filtering functionalities.

### 5. Notification Service
Sends notifications to users for various events, such as order confirmations, status updates, and promotional messages. Notifications can be sent via email, SMS, or push notifications.

### 6. Order Service
Manages the entire order lifecycle, including order creation, payment processing, order status tracking, and managing order histories.

### 7. Product Service
Manages product information, including product details, categories, inventory status, and pricing.

### 8. Store Service
Oversees store-related operations, allowing the addition, management, and tracking of stores and their respective inventories.

### 9. User Service
Handles user-related operations such as authentication, registration, profile management, and authorization.

## Technology Stack
- **Backend**: Spring Boot, Spring Data JPA
- **Service Discovery**: Spring Cloud Eureka
- **Gateway**: Spring Cloud Gateway
- **Database**: MySQL, PostgreSQL
- **Containerization**: Docker 
- **Security**: JWT, OAuth2 
