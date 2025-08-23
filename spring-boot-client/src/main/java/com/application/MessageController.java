package com.application;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private Counter counter;

    @Autowired
    private MessageService messageService;

    public MessageController(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("message.requests.spring.application");
    }

    @GetMapping("/message")
    public String getMessage() {
        logger.info("/message has been visited");
        counter.increment();
        return messageService.getMessage();
    }
}
