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
- [Installation](#installation-and-running-application)
- [API Documentation](#api-documentation)
- [Security](#security)
- [Links](#link)

## Overview

The Recruitment Application is designed to streamline the process of job postings and employer management. It leverages modern technologies and best practices in Java and Spring Boot development, providing a robust and scalable solution for managing recruitment data.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete operations for Jobs, Employers, Seekers and Resumes.
- **Authentication**: Secure authentication using JWT and OAuth2 Github
- **Caching**: Integration with Redis for efficient caching.
- **API Documentation**: Comprehensive API documentation with Swagger UI.
- **Logging**: Detailed logging using Log4j2.

## Requirements

- Java 17 or higher
- Maven
- Docker Desktop

## Installation and Running application:
1. Clone the repository:

   git clone https://github.com/HoangNguyendotcom/BEC_repo.git

2. Import database to MySQL:
   - Run MySQL on Docker:
     docker compose up -d mysql-db
   
   - Get the container_id : 
      docker ps
   
   - Copy the SQL file to the Container:
     docker cp job_db.sql <container_id>:/job_db.sql
   
   - Access the Container:
     docker exec -it <container_id> /bin/sh
   
   - Import the Database:
     mysql -u root -p job_db < /job_db.sql

3. Running the app using Docker:
   docker compose up --build

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

## Link:
   Link swagger doc
   http://localhost:8080/swagger-ui/index.html

   Link prometheus metrics
   http://localhost:8080/actuator/prometheus

   Link console prometheus
   http://localhost:9090/

   Link console grafana
   http://localhost:3000/
    
