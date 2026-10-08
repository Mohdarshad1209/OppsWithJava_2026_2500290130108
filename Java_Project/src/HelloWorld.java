package com.example.MyFirstSpringProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
    public static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public String sayHello() {
        logger.info("Program run successfully");
        logger.warn("Give valid input");
        logger.error("Wrong input provided");
        return "Hello, World!";
    }
}