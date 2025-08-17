# Spring Boot Observability

Spring Boot application that increments a counter when user visits `GET /message`.

Micrometer is a facade that is used to integrate Spring Boot Actuator metrics with external monitoring systems like Prometheus.

# How it works
## Counter
- Metrics endpoint is exposed on `/actuator/prometheus` with job name as `spring-actuator` that increments a counter upon each visit
- Metric is `http_server_requests_seconds_count{exception="None", instance="host.docker.internal:8080", job="spring-actuator", method="GET", outcome="SUCCESS", status="200", uri="/message"}`

## Prometheus
- Prometheus exposes its own metrics on `/metrics:9090` with job name as `prometheus`

## Tracing:
- Uses Zipkin OpenTelemetry exporter with Micrometer to send traces to Zipkin
- Run `docker run -p 9411:9411 openzipkin/zipkin` and navigate to `localhost:9411` and see all traces