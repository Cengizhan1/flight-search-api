# FLIGHT SEARCH API

This project is developed using Java Spring Boot framework with a layered architecture. The project utilizes Java's Hibernate, Security, and Spring Data frameworks. Additionally, JWT (JSON Web Token) services are used for authentication and authorization processes. On the front-end side, the JavaScript Axios library is employed for API communication.

## Project Overview

The main objective of this project is to provide a platform for users to manage their tasks within projects. Users can register and log in, create projects, and add tasks to these projects. The task screen offers various filtering and search options to enhance user experience.

## Technologies Used

* **Java Spring Boot**: Provides the foundation for the project, offering a robust and scalable framework.
* **Hibernate**: Used for object-relational mapping and database interactions.
* **Spring Data**: Simplifies database access and management.
* **Spring Security**: Ensures secure authentication and authorization processes.
* **JWT (JSON Web Token)**: Used for secure token-based user authentication.
* **Swagger**: Provides a convenient way to document and test APIs.
* **Postman**: Used for API testing and development.
* **MySQL**: The relational database used to store project and task information.
* **ModelMapper**: Simplifies object mapping between DTOs (Data Transfer Objects) and entities.
* **RestTemplate**: Facilitates communication with external APIs.

## Automatic Flight Data Update

One distinctive feature of this project is the automatic update of flight data. Every 24 hours, the system sends requests to an external API to fetch information about airports and flight details. This data is then stored in the project's database, allowing users to search for and access relevant flight information.

## Requirements
```
- Java 17 or higher
- Database (default is MySQL)
```


## Installation
1. Clone the project:

```
git clone https://github.com/Cengizhan1/flight-search-api
``` 

2. Navigate to the project directory:

```
cd flight-search-api
``` 

## Project Setup
1- Database Setup: \
MySQL is used as the default database. To configure your database to MySQL, edit the application.properties file. \
2- Start the Project: \
To start the Spring Boot application, run the following command:
```
./mvnw spring-boot:run
``` 

## API Documentation
You can find the API documentation here.
It provides detailed information on available routes and their usage.

postman : https://documenter.getpostman.com/view/16991416/2s9Yyqj2yH \
swagger : http://localhost:8080/swagger-ui/index.html

## Contributers
Cengizhan Yavuz \
Email : cengizhany.cy@gmail.com
