#!/bin/bash

# Copiar .env.example a .env si no existe
if [ ! -f .env ]; then
  cp .env.example .env
  echo "Archivo .env creado a partir de .env.example"
fi

# Levantar el back y la db con Docker Compose
docker-compose up --build
