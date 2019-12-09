package com.tbz.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bootstrap {

    private static Logger log = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        log.warn("startup begin.");
        long beginTime = System.currentTimeMillis();
        SpringApplication.run(Bootstrap.class, args);
        log.warn("startup end, cost = {}ms.", System.currentTimeMillis() - beginTime);
    }
}
