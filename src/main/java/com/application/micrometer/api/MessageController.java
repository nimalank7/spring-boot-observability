package com.application.micrometer.api;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private Counter counter;

    public MessageController(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("message.requests.spring.application");
    }

    @GetMapping("/message")
    public String getMessage() {
        counter.increment();
        return "Working...!!";
    }
}
