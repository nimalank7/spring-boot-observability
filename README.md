# Spring Boot Observability

Spring Boot microservice application consisting of a client and server which returns "Hello from spring-boot-server!" when a user visits
`GET /message`. The application is instrumented with logs, metrics and traces.

This is largely based off https://spring.io/blog/2022/10/12/observability-with-spring-boot-3.

# How it works
## Build and run the application

1. Build the application

```
docker compose build
```

2. Run the application

```
docker compose run --detach
```

3. Receive a message

```
localhost:8080/message
```

# Instrumentation
## Logging

Logs are sent to STDOUT and to Loki. `logback-spring.xml` is the Logback appender that sends logs to Loki and automatically 
adds `traceId` and `spanID` as labels for Loki to search on.

To view the Docker logs:

```
docker logs -f <container name>
```

To view Loki logs navigate to Grafana:

```
localhost:3100
```

Add Loki as data source to Grafana with the URL as `http://loki:3100`

Navigate to `Explore`, select `Loki`, then select the code view and choose `Last 1 hour`. Run the following query to 
see all logs:

```
{app=~".+"}
```

To see logs from the application (e.g. `spring-boot-client`) run:

```
{app="spring-boot-client"}
```

## Metrics 
### Counter
Spring Boot Actuator configures Micrometer which is a instrumentation facade.

- Both `spring-boot-client` and `spring-boot-server` have metrics exposed on `/actuator/prometheus` which Prometheus consumes
- Both set up a counter metric (`http_server_requests_seconds_count`) that increments on each message endpoint
- To see metrics in Prometheus navigate to:

```
localhost:9091
```

### Prometheus
- Prometheus exposes its own metrics on port `/metrics` with job name as `prometheus`

## Tracing:

- Spring Boot Actuator and Micrometer are configured using `management.zipkin.tracing.endpoint` to use the Open Telemetry Zipkin exporter 
which sends traces to Zipkin on `http://zipkin:9411/api/v2/spans`
- `spring-boot-client` creates its spans and adds a `traceId` which is propagated across to `spring-boot-server`. 
- `spring-boot-server` creates its spans and adds the `traceId` which it received
- Both emit their spans to Zipkin which uses the `traceID` to put them together into a trace.
- `@Observed` annotation adds a custom span to surface both `MessageService.getMessage()` and `MessageService.retrieveMessage()` in the trace
- To see all traces in Zipkin navigate to:

```
localhost:9411
```