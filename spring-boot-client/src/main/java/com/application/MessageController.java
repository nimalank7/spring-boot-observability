package com.application;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Log logger = LogFactory.getLog(MessageController.class);

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
