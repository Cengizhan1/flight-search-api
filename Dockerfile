FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR flight-search-api
COPY --from=build target/*.jar flight-search-api.jar
ENTRYPOINT ["java", "-jar", "flight-search-api.jar"]