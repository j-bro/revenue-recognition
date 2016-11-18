#!/usr/bin/env bash
set -e

echo "Building DB Image"
docker build -t j-bro/revenue-recognition-sql database/

echo "Building Server Image"
./gradlew buildImage

echo "Starting Application"
docker-compose up
