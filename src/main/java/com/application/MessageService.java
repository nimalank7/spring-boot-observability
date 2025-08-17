package com.application;

import io.micrometer.observation.annotation.Observed;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final Log logger = LogFactory.getLog(MessageController.class);

    @Observed(contextualName = "getMessage")
    public String getMessage() {
        logger.info("Inside MessageService.getMessage()");
        return "Hello from Message Controller!";
    }
}
