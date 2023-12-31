# Use a base image with Java 11
#FROM adoptopenjdk:11-jre-hotspot
FROM eclipse-temurin:17-jdk-jammy

LABEL authors="jmoyano@intelix.ad"

# Set the working directory
WORKDIR /app
# Copy the JAR file to the container
COPY target/*.jar app.jar
# Expose the port that your Spring Boot application listens on (default is 8080)
EXPOSE 8086
# Define the command to run your application
CMD ["java", "-jar", "app.jar"]