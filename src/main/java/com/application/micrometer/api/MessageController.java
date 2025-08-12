package com.application.micrometer.api;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private Counter counter;

    public MessageController(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("message.requests.spring.application");
    }

    @GetMapping("/message")
    public String getMessage() {
        logger.info("/message has been visited");
        counter.increment();
        return "Working...!!";
    }
}
