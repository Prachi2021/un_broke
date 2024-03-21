package com.prachi.un_broke;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoggerClass {
    private static final Logger logger = LoggerFactory.getLogger(LoggerClass.class);

    public static void provideInfo(String message){
        logger.info(message);
    }

    public static void provideError(String message){
        logger.error(message);
    }
}
