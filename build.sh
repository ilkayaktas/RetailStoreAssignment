#!/bin/bash

export MONGODB_IP=localhost
export MONGODB_USER=root
export MONGODB_PASSWORD=passw0rd

## mvn build

containerId=$(docker stop ilkayaktas/retailstore)

# Remove container
docker rm $containerId

# Remove image
docker rmi ilkayaktas/retailstore

# Build image
docker build --build-arg JAR_FILE=build/libs/\*.jar -t ilkayaktas/retailstore .

# docker run --name retailstore --restart=unless-stopped -p 8080:8080 -e MONGODB_IP=mongodb -e MONGODB_USER=root -e MONGODB_PASSWORD=passw0rd ilkayaktas/retailstore