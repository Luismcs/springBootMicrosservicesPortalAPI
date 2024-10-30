package com.exercise.manager.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerPrinter {

    private static Logger logger = LoggerFactory.getLogger(LoggerPrinter.class);

    public static void loggerPrinter(String message) {
        logger.info(message);
    }

}
