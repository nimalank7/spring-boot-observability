# Spring Boot Micrometer

Spring Boot application that increments a counter when user visits `/message`.

Micrometer is a facade that is used to integrate Spring Boot Actuator metrics with external monitoring systems like Prometheus.

# How it works
## Counter
- Metrics endpoint is exposed on `/actuator/prometheus:8080` with job name as `spring-actuator` that increments a counter upon each visit

## Prometheus
- Prometheus exposes its own metrics on `/metrics:9090` with job name as `prometheus`

