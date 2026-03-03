# Use official OpenJDK image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar into container
COPY target/hospital-management-0.0.1-SNAPSHOT.jar app.jar

# Expose port (match your app port)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]