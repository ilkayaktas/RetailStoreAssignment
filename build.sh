#!/bin/bash

export MONGODB_IP=localhost
export MONGODB_USER=root
export MONGODB_PASSWORD=passw0rd

# Mongodb is run in this docker-compose to run all integration tests.
docker-compose -f docker-compose-for-build.yml up -d

mvn clean
mvn verify

containerId=$(docker stop ilkayaktas/retailstore)

# Remove container
docker rm $containerId

# Remove image
docker rmi ilkayaktas/retailstore

# Build image
docker build --build-arg JAR_FILE=target/\*.jar -t ilkayaktas/retailstore .

docker-compose -f docker-compose-for-build.yml down

# docker tag ilkayaktas/retailstore ilkayaktas/retailstore
# docker image push ilkayaktas/retailstore
# docker run --name retailstore --network=backend-network --restart=unless-stopped -p 8090:8090 -e MONGODB_IP=mongodb -e MONGODB_USER=root -e MONGODB_PASSWORD=passw0rd ilkayaktas/retailstore