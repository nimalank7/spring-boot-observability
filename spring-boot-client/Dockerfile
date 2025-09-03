FROM maven:3-eclipse-temurin-24@sha256:2a2e7f8a91a0598d64f75ea2c470dad84a3fb7bc1895ea4d1f3504c2554f8fc9 AS builder

# Caches the dependencies as pom.xml rarely changes
COPY pom.xml .
RUN ["mvn", "dependency:copy-dependencies"]

COPY src ./src
RUN ["mvn", "clean", "package"]

FROM maven:3-eclipse-temurin-24@sha256:2a2e7f8a91a0598d64f75ea2c470dad84a3fb7bc1895ea4d1f3504c2554f8fc9

COPY --from=builder /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]