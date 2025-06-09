# Javatech

A Spring Boot project implementing RESTful APIs for Customers and Products.
Project Name : Javatech

## Features
- Java 17, Spring Boot 3.3.5
- Lombok
- MS SQL database
- Spring Data JPA
- Swagger UI (OpenAPI)
- Structured logging with Logback
- Global exception handling
- Unit test cases
- Github Actions

## Setup
1. Update `src/main/resources/application.properties` with your MS SQL credentials.
2. Create Database 'CREATE DATABASE javatech'.
3. Check for the MS sql TCP port.
4. Run using Maven: `mvn spring-boot:run`
5. Access Swagger UI at: `http://localhost:8080/swagger-ui.html`
