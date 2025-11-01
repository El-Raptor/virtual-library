# ===========================
# Stage 1: Build the JAR
# ===========================
FROM maven:3.9.11-eclipse-temurin-25 AS builder

# Set working directory
WORKDIR /app

# Copy only the pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ===========================
# Stage 2: Run the JAR
# ===========================
FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

# Copy only the final JAR from builder stage
COPY --from=builder /app/target/virtual-library.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run optimized for containers
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]