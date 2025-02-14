# Use OpenJDK 17 slim as base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target folder to the container and rename it
COPY target/System_health_monitor_and_alert-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default Spring Boot port)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
