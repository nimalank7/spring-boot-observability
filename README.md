# Spring Boot Observability

Spring Boot microservice application consisting of a client and server which returns "Hello from spring-boot-server!" when a user visits
`GET /message`. The application is instrumented with logs, metrics and traces.

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

Logs are sent to STDOUT. Spring Boot also correlates the logs with traces by adding `traceId` and `spanId`. To view logs run:

```
docker logs -f <container name>
```

## Metrics 
### Counter
Spring Boot Actuator configures Micrometer which is a instrumentation facade.

- Both `spring-boot-client` and `spring-boot-server` have metrics exposed on `/actuator/prometheus` which Prometheus consumes
- Both set up a counter metric (`http_server_requests_seconds_count`) that increments on each message endpoint

### Prometheus
- Prometheus exposes its own metrics on port `/metrics` with job name as `prometheus`
- To see metrics in Prometheus navigate to:

```
localhost:9091
```

## Tracing:

- Spring Boot Actuator and Micrometer are configured to use the Open Telemetry Zipkin exporter 
which sends traces to Zipkin on `http://zipkin:9411/api/v2/spans`
- `spring-boot-client` creates its spans and adds a `traceId` which is propagated across to `spring-boot-server`. 
- `spring-boot-server` creates its spans and adds the `traceId` which it received
- Both emit their spans to Zipkin which uses the `traceID` to put them together into a trace.
- `@Observed` annotation adds a custom span to surface both `MessageService.getMessage()` and `MessageService.retrieveMessage()` in the trace
- To see all traces in Zipkin navigate to:

```
localhost:9411
```


