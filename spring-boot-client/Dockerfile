FROM maven:3-eclipse-temurin-25@sha256:42f00a9b0a9e04389ba857b2a69ba47fe11d1d47f7565edd65c45bbbd6b1e639 AS builder

# Caches the dependencies as pom.xml rarely changes
COPY pom.xml .
RUN ["mvn", "dependency:copy-dependencies"]

COPY src ./src
RUN ["mvn", "clean", "package"]

FROM maven:3-eclipse-temurin-25@sha256:42f00a9b0a9e04389ba857b2a69ba47fe11d1d47f7565edd65c45bbbd6b1e639

COPY --from=builder /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]