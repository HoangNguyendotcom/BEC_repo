# Recruitment Application

This is a Spring Boot application for managing job postings and employers. It includes functionalities for CRUD operations, JWT-based authentication, OAuth2 login, and integrates with Redis for caching. The application also provides API documentation using SpringDoc OpenAPI.
- Contact:
    - Nguyen Dac Hoang 
    - Email: ndhoang7089@gmail.com 
    - Github: https://github.com/HoangNguyendotcom/
    
## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Security](#security)

## Overview

The Recruitment Application is designed to streamline the process of job postings and employer management. It leverages modern technologies and best practices in Java and Spring Boot development, providing a robust and scalable solution for managing recruitment data.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete operations for Jobs and Employers.
- **Authentication**: Secure authentication using JWT and OAuth2 Github
- **Caching**: Integration with Redis for efficient caching.
- **API Documentation**: Comprehensive API documentation with Swagger UI.
- **Logging**: Detailed logging using Log4j2.

## Requirements

- Java 11 or higher
- Maven
- MySQL
- Redis

## Installation

1. Clone the repository:

    git clone https://github.com/HoangNguyendotcom/BEC_repo

   3. Update the database configuration in `src/main/resources/application.yml`:

       ```properties.yml
       spring:
         datasource:
               url: jdbc:mysql://localhost:3306/job_db?serverTimezone=UTC&useSSL=false #Change to your database
               driverClassName: com.mysql.cj.jdbc.Driver
               username: root  #Change to your user
               password: Admin@123 #Change to your password

4. Install the dependencies and build the project:

    ./mvnw clean install


## Running the application
1. Running the app using Maven:

   ./mvnw spring-boot:run

2. Or build jar file and run it:

   ./mvnw clean package
   java -jar target/recruitment-0.0.1-SNAPSHOT.jar

## API Documentation:
    
  The API Documentation is avaiable at http://localhost:8080/swagger-ui-custom.html after running the application.

## Security:
1. Using JwT Tokens for Postman:
   - In Postman, You need to get the full authentication by obtaining a JWT token 
            by sending a POST request to the login endpoint (/login) with valid credentials.
     POST http://localhost:8080/auth/login
     Headers:
            Content-Type: application/json
     Body:
          {
          "username": "user",
          "password": "password"
          }
   - After getting the token, add the header Authentication for your request:
     Headers:
            Authorization: Bearer your-jwt-token
   - Now you can make authenticated requests to your secured endpoints.

2. Using Oauth2 by GitHub for web:
    - Access the URL: http://localhost:8080/login
    - Login by Github
    
