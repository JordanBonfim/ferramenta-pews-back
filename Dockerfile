# Etapa de testes
FROM maven:3.9.9-amazoncorretto-21 AS test
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn test

# Etapa de build
FROM maven:3.9.9-amazoncorretto-21 AS build
WORKDIR /app
COPY --from=test /app .
RUN mvn clean package

# Etapa final
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/ferramenta-pews-back-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/ferramenta-pews-back-0.0.1-SNAPSHOT.jar"]
