package com.application.micrometer.api;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String getMessage() {
        return "Hello from Message Controller!";
    }
}
