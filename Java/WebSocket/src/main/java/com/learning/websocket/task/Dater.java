package com.learning.websocket.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Dater {
    Logger log = LoggerFactory.getLogger(Dater.class);

    @Scheduled(fixedRate = 1000) // 1 time per second
    public void sendDate() {
        log.info("Update date to client");

    }
}
