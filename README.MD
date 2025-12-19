## POS with Spring Boot

POS made with Spring Boot, Maven, Lombok, React, and TypeScript

## Features

- **Backend**: Spring Boot 3.3.0 with PostgreSQL
- **Frontend**: React + TypeScript + Vite
- **Authentication**: Clerk JWT integration
- **API Documentation**: Swagger/OpenAPI
- **Health Checks**: Spring Boot Actuator
- **Docker**: Full containerization support

## Recent Updates

### Backend Improvements
- ✅ Updated Spring Boot from 3.5.5-SNAPSHOT to stable version 3.3.0
- ✅ Added Spring Boot Actuator for health checks
- ✅ Updated Dockerfile to use stable Eclipse Temurin JDK 21
- ✅ Fixed Docker Compose healthcheck configuration
- ✅ Cleaned up unused imports in Java files

## Instructions

### Prerequisites

1) Install Java 21 JDK
2) Install Maven 3.9+
3) Install Node.js 18+ and npm/pnpm
4) Install Docker and Docker Compose (optional, for containerized setup)

### Backend Setup

1) Create a PostgreSQL database. You can use Docker:
   ```bash
   cd infra
   docker-compose -f docker-compose.db.yml up -d
   ```

2) Configure database connection in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   spring.jpa.hibernate.ddl-auto=update
   ```

3) Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

4) The backend will be available at:
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui/index.html
   - Health Check: http://localhost:8080/actuator/health

### Frontend Setup

1) Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2) Install dependencies:
   ```bash
   npm install
   # or
   pnpm install
   ```

3) Create a `.env` file with:
   ```env
   VITE_API_BASE_URL=http://localhost:8080
   VITE_OPENAPI_URL=http://localhost:8080/v3/api-docs
   ```

4) Run the development server:
   ```bash
   npm run dev
   # or
   pnpm dev
   ```

5) The frontend will be available at http://localhost:3000

### Docker Setup (Full Stack)

1) Navigate to the infra directory:
   ```bash
   cd infra
   ```

2) Create a `.env` file with:
   ```env
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   POSTGRES_DB=postgres
   ```

3) Start all services:
   ```bash
   docker-compose -f docker-compose.dev.yml up -d
   ```

4) For production:
   ```bash
   docker-compose -f docker-compose.prod.yml up -d
   ```

## Project Structure

```
spring/
├── frontend/          # React + TypeScript frontend
├── infra/             # Docker configurations
├── src/               # Spring Boot backend source code
└── pom.xml           # Maven dependencies
```

## Technologies

- **Backend**: Spring Boot 3.3.0, Spring Security, Spring Data JPA
- **Database**: PostgreSQL 14
- **Frontend**: React 18, TypeScript, Vite, TanStack Query
- **Authentication**: Clerk
- **Build Tools**: Maven, npm/pnpm
- **Containerization**: Docker, Docker Compose
