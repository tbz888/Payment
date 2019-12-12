package com.tbz.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableScheduling
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
     * 启动容器后的初始化回调函数，要避免线程阻塞，以免影响启动.
     */
    @Bean
    public CommandLineRunner initModule() {
        return args -> {
            log.warn("Module init begin.");
            long current = System.currentTimeMillis();
            // 依次加载的模块
            log.warn("Module init end, cost = {}ms.", System.currentTimeMillis() - current);
        };
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
