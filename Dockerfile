# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn package -DskipTests

# Stage 2: Create a lightweight image with only the JAR file
FROM amazoncorretto:17-alpine3.16
WORKDIR /app
COPY --from=build /app/target/binary-saerch-tree-api-0.0.1-SNAPSHOT.jar /app/binary-saerch-tree-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "binary-saerch-tree-api-0.0.1-SNAPSHOT.jar"]
