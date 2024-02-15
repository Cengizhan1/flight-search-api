# FLIGHT SEARCH API

This project is developed using Java Spring Boot framework with a layered architecture. The project utilizes Java's Hibernate, Security, and Spring Data frameworks. Additionally, JWT (JSON Web Token) services are used for authentication and authorization processes. On the front-end side, the JavaScript Axios library is employed for API communication.

## Project Overview

The main objective of this project is to provide a platform for users to manage their tasks within projects. Users can register and log in, create projects, and add tasks to these projects. The task screen offers various filtering and search options to enhance user experience.

## Technologies Used

- **Java Spring Boot**: Provides the foundation for the project, offering a robust and scalable framework.
- **Hibernate**: Used for object-relational mapping and database interactions.
- **Spring Data JPA**: Simplifies database access and management.
- **Spring Security**: Ensures secure authentication and authorization processes.
- **JWT (JSON Web Token)**: Used for secure token-based user authentication.
- **Swagger**: Provides a convenient way to document and test APIs.
- **Postman**: Used for API testing and development.
- **H2 In Memory Database**:Database
- **ModelMapper**: Simplifies object mapping between DTOs (Data Transfer Objects) and entities.
- **RestTemplate**: Facilitates communication with external APIs.
- **Resilience4j**: Used to increase application resilience.
- **Open API Documentation**: Used to document and share APIs.
- **Spring Data JPA**: Used for database access and management.
- **Maven**: Used for project management and dependency management.
- **Junit5**: Used to write unit tests.
- **Integration Tests**: Used to write and execute integration tests.
- **Docker**: Used to containerize and deploy the application.
- **Docker Compose**: Used to define and run multi-container applications.
- **Prometheus**: Used for system monitoring and alerts.
- **Grafana**: Used to visualize and monitor data.
## Automatic Flight Data Update

One distinctive feature of this project is the automatic update of flight data. Every 24 hours, the system sends requests to an external API to fetch information about airports and flight details. This data is then stored in the project's database, allowing users to search for and access relevant flight information.

## Requirements
```
- Java 17 or higher
- Database (H2 in memory Database)
```


## Prerequisites

---
- Maven or Docker
---

## Docker Run
The application can be built and run by the `Docker` engine. The `Dockerfile` has multistage build, so you do not need to build and run separately.

Please follow the below directions in order to build and run the application with Docker Compose;

```sh
$ cd flight-search-api
$ docker-compose up -d
```

Docker compose creates 3 replicas (instances)

### Prometheus
#### You can reach prometheus page via `http://{HOST}:9090`
### Grafana
#### You can reach grafana page via `http://{HOST}:3000` - GF_SECURITY_ADMIN_PASSWORD=admin

---
## Maven Run
To build and run the application with `Maven`, please follow the directions below;

```sh
$ cd flight-search-api
$ mvn clean install
$ mvn spring-boot:run
```
You can reach the swagger-ui via  `http://{HOST}:8080/swagger-ui.html`

---