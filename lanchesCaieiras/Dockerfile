# Etapa 1: Construção
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /lanchesCaieiras
COPY pom.xml .
COPY src ./src
RUN mvn clean test package

# Etapa 2: Execução
FROM openjdk:17-jdk-slim
WORKDIR /lanchesCaieiras

# Adicionar variáveis de ambiente
ARG ENV_FILE=.env
RUN export $(cat $ENV_FILE | xargs)

COPY --from=build /lanchesCaieiras/target/*.jar lanchesCaieiras.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lanchesCaieiras.jar"]