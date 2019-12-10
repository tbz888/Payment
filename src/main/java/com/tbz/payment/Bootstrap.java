package com.tbz.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class Bootstrap {

    // slf4j的日志接口
    private static Logger log = LoggerFactory.getLogger(Bootstrap.class);

    /**
     * 启动入口
     *
     * @param args
     */
    public static void main(String[] args) {
        log.warn("app startup begin.");
        long beginTime = System.currentTimeMillis();
        SpringApplication.run(Bootstrap.class, args);
        log.warn("app startup end, cost = {}ms.", System.currentTimeMillis() - beginTime);
    }

    /**
     * 关闭前的回调函数
     */
    @PreDestroy
    public static void destructor() {
        log.warn("app shutdown begin.");
        log.warn("app shutdown end.");
    }
}
