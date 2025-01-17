# Use official Maven image to build the application
FROM maven:latest AS build

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .

# Download project dependencies (for caching purposes)
RUN mvn dependency:go-offline

# Copy the entire source code into the container
COPY src ./src

# Build the Spring Boot application (this generates the JAR file)
RUN mvn clean package -DskipTests

# Use a lightweight openjdk image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on (default for Spring Boot is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]