# ------------------------------
# Stage 1: Build JAR
# ------------------------------
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Gradle wrapper + source 복사
COPY . .

# gradlew 실행권한 부여
RUN chmod +x ./gradlew

# Boot Jar 빌드
RUN ./gradlew bootJar -x test

# ------------------------------
# Stage 2: Runtime
# ------------------------------
FROM eclipse-temurin:21-jre
WORKDIR /app

# builder에서 생성된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
