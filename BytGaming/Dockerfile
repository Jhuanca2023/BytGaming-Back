FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy pom.xml first for better caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests -B

# Verify JAR was created
RUN ls -la target/ && \
    if [ ! -f target/app.jar ]; then \
        echo "ERROR: JAR file not found!" && \
        echo "Files in target directory:" && \
        ls -la target/ && \
        exit 1; \
    fi && \
    echo "SUCCESS: JAR file found" && \
    ls -lh target/app.jar

FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
RUN apk add --no-cache curl
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /workspace/target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
