# Use a base image with Java and Gradle pre-installed
FROM gradle:jdk17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files and source code into the container
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the project
RUN gradle build --no-daemon

# Use a lightweight base image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]