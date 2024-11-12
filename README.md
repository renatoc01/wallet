# Wallet
Wallet Service Assignment

# Instructions on how to install, test, and run the service.

First of all, at the root of the code, you need to execute the command to generate the image. (You need to have maven installed on the computer with environment variable setted)

```sh
mvn clean install
```

I pushed the docker-compose.yml and you just need to execute the command.
(You need to have the Docker installed on the computer)

```sh
docker-compose up
```

After the deploy of the database and the application, you can test the link below to see the swagger document.

```sh
http://localhost:8090/swagger-ui/index.html
```

I also pushed the postman collection to test all the endpoints using postman software, the file called "Recarga Pay.postman_collection.json".

# A written explanation of the design choices, detailing how the implementation meets both functional and non-functional requirements.

I decided to use the respectives technologies to develop all the application:

 - Java 17
 - Spring Boot
 - Spring Data
 - Junit
 - Mockito
 - Swagger
 - Lombok
 - MapStruct
 - SLF4J
 - MySQL
 - Docker
 - Hexagonal Architecture
 - Rest
