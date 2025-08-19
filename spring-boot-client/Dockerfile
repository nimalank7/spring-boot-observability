FROM maven:3.9.11-eclipse-temurin-24@sha256:db74cfbb321f7c1c0ffa735d188dc4215a279e8f677fd62aed4a955606face85 AS builder

# Caches the dependencies as pom.xml rarely changes
COPY pom.xml .
RUN ["mvn", "dependency:copy-dependencies"]

COPY src ./src
RUN ["mvn", "clean", "package"]

FROM maven:3.9.11-eclipse-temurin-24@sha256:db74cfbb321f7c1c0ffa735d188dc4215a279e8f677fd62aed4a955606face85

COPY --from=builder /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]