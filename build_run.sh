#!/usr/bin/env bash
set -e

if [ $1 = "clean" ]; then
    echo "Removing existing containers"
    docker rm revenuerecognition_mariadb_1
    docker rm revenuerecognition_server_1
fi

echo "Building DB Image"
docker build -t j-bro/revenue-recognition-sql database/

echo "Building Server Image"
./gradlew buildImage

echo "Starting Application"
docker-compose up
