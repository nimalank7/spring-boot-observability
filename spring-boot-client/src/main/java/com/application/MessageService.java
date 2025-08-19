package com.application;

import io.micrometer.observation.annotation.Observed;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MessageService {

    private static final Log logger = LogFactory.getLog(MessageController.class);

    @Autowired
    private RestClient restClient;

    @Observed(contextualName = "getMessage")
    public String getMessage() {
        logger.info("Inside MessageService.getMessage()");

        return restClient.get().retrieve().body(String.class);
    }
}
