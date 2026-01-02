#!/bin/bash
export ENVIRONMENT=development
export SPRING_PROFILE=development
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/mydatabase

docker compose -f ./../docker-compose.dev.yml down -v 

echo "=========================================="
echo "Services are going down..."
echo "=========================================="

# Show logs
echo "Showing logs... (Ctrl+C to exit)"
docker compose logs -f
