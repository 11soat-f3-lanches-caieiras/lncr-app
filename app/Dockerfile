# Etapa 1: Construção
FROM maven:3.8.5-openjdk-17 AS build
ARG LNCR_MAVEN_ACTOR
ARG LNCR_MAVEN_PAT
ARG DEBUG
ENV LNCR_MAVEN_ACTOR=$LNCR_MAVEN_ACTOR LNCR_MAVEN_PAT=$LNCR_MAVEN_PAT DEBUG=$DEBUG
WORKDIR /app
COPY /app .
#RUN env &&  \
#    mkdir -p ~/.m2 && \
#    cp maven_settings.xml ~/.m2/settings.xml  && \
#    mvn clean test package -s ~/.m2/settings.xml $DEGUG
RUN mvn clean test package -s maven_settings.xml $DEGUG

# Etapa 2: Execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
