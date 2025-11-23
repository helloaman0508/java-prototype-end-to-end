# ---------- Builder stage ----------
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace

# copy pom and download dependencies first (cache)
COPY pom.xml ./
RUN mvn -B -ntp dependency:go-offline

# copy source, build jar
COPY src ./src
RUN mvn -B -ntp -DskipTests package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# create non-root user
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

COPY --from=builder /workspace/target/*.jar app.jar
RUN chown appuser:appgroup /app/app.jar && chmod 0400 /app/app.jar

USER appuser
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]