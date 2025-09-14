# Etapa 1: Construção
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /java
COPY pom.xml .
COPY core ./core
COPY app ./app
RUN mvn clean test package

# Etapa 2: Execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /java/app/target/app*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
