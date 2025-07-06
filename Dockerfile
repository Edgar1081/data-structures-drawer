# Building application
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

# Running the app
FROM openjdk:17-slim

WORKDIR /drawer

COPY --from=builder /build/target/proyecto2.jar proyecto2.jar

ENTRYPOINT ["java", "-jar", "proyecto2.jar"]
