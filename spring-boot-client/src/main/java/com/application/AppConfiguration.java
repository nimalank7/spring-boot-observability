package com.application;


import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
public class AppConfiguration {

    // To use @Observed
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    // To ensure the tracing is propagated across
    @Bean
    RestClient restClient (RestClient.Builder builder) {
        return builder
                .baseUrl(System.getenv("SPRING_SERVER_URI") + "/retrieveMessage")
                .build();
    }
}
