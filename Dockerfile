# Start from an OpenJDK image with Java 17 (or your required version)
FROM eclipse-temurin:17-jdk-alpine

# Copy the jar built by Maven to the container
COPY target/*.jar app.jar

# Expose port 8080 (or your Spring Boot server port)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
