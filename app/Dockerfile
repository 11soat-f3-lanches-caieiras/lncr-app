# Etapa 1: Construção
FROM maven:3.8.5-openjdk-17 AS build
ARG LNCR_MAVEN_ACTOR
ARG LNCR_MAVEN_TOKEN
ARG DEBUG
ENV LNCR_MAVEN_ACTOR=$LNCR_MAVEN_ACTOR LNCR_MAVEN_TOKEN=$LNCR_MAVEN_TOKEN DEBUG=$DEBUG
WORKDIR /app
COPY /app .
RUN echo $PWD && ls -la && mvn clean test package -s maven_settings.xml $DEGUG
#RUN mvn clean test package -s maven_settings.xml $DEGUG




# Etapa 2: Execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
