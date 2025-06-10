#######################
# 1️⃣ Build stage
#######################
FROM gradle:8-jdk17 AS builder
WORKDIR /workspace

# copy only Gradle wrapper & build files first (enables Docker cache)
COPY gradle gradle
COPY gradlew .
RUN chmod +x gradlew
COPY build.gradle.kts settings.gradle.kts ./

# Pre-download dependencies
RUN ./gradlew --no-daemon dependencies

# now copy the rest of the source
COPY src src

# build the fat jar
RUN ./gradlew --no-daemon bootJar

########################
# 2️⃣ Runtime stage
########################
FROM eclipse-temurin:17-jre
WORKDIR /app

# copy the JAR from builder stage
COPY --from=builder /workspace/build/libs/*.jar app.jar

# Cloud Run provides $PORT; Spring Boot binds to it by default (8080)
ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
