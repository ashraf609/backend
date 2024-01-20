# Build stage
FROM maven:3.8.5-openjdk-17 AS build
# Copy the local source to the container
COPY . .
# Package the application
RUN mvn clean package -DskipTests
# Verify the contents of the target directory (you can remove this after confirming that JAR is created)
RUN ls /target

# Run stage
FROM openjdk:17.0.1-jdk-slim
# Copy the JAR from the build stage to the run stage
COPY --from=build /target/InoviceSpringBootMvcApp-0.0.1-SNAPSHOT.jar app.jar
# Expose the port the app runs on
EXPOSE 8082
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
