#!/bin/bash
export ENVIRONMENT=development
export SPRING_PROFILE=development
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/mydatabase

docker compose -f ./../docker-compose.dev.yml up --build -d

echo "=========================================="
echo "Services are starting..."
echo "Postgres: localhost:5432"
echo "Spring Boot API: localhost:8080"
echo "=========================================="

# Show logs
echo "Showing logs... (Ctrl+C to exit)"
docker compose logs -f
