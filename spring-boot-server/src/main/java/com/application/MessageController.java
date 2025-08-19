package com.application;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Log logger = LogFactory.getLog(MessageController.class);

    @Autowired
    private MessageService messageService;

    @GetMapping("/retrieveMessage")
    public String getMessage() {
        logger.info("/retrieveMessage has been visited");
        return messageService.retrieveMessage();
    }
}
