# Use OpenJDK 17 slim as base image
FROM openjdk:17-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml for dependency resolution
COPY mvnw mvnw.cmd ./
COPY .mvn .mvn
COPY pom.xml .

# Grant permission to execute the wrapper script
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the source code and build the application
COPY src src
RUN ./mvnw package -DskipTests

# Use a separate image for running the app
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
