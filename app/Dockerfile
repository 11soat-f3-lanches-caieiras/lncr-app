# Etapa 1: Construção
FROM maven:3.8.5-openjdk-17 AS build
ARG LNCR_MAVEN_ACTOR
ARG LNCR_MAVEN_TOKEN
ARG DEGUG
ENV LNCR_MAVEN_ACTOR=$LNCR_MAVEN_ACTOR LNCR_MAVEN_TOKEN=$LNCR_MAVEN_TOKEN DEBUG=$DEGUG
WORKDIR /app
COPY . .
RUN mkdir -p ~/.m2
COPY app/maven_settings.xml ~/.m2/settings.xml
RUN mvn clean test package -s ~/.m2/settings.xml $DEBUG

# Etapa 2: Execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
