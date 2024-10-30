package com.exercise.authentication.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerPrinter {

    private static final Logger logger = LoggerFactory.getLogger(LoggerPrinter.class);

    public static void printLog(String message) {
        logger.info(message);
    }

}
