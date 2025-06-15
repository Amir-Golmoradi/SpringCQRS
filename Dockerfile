# Use a slim JDK image for smaller footprint
FROM eclipse-temurin:17-jdk-jammy as builder

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the jar from the builder image
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]